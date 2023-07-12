package com.example.toystore.service;

import com.example.toystore.dto.Product;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.List;


@Service
public class QRService {
    final static int IMAGE_WIDTH = 2480;
    final static int IMAGE_HEIGHT = 3508;

    final static int QR_WIDTH = 100;
    final static int QR_HEIGHT = 100;

    final static int WIDTH_ITEM = 200;
    final static int HEIGHT_ITEM = 200;

    final static int row = 10;
    final static int col = 7;

    final static int SPACE = 20;

    public void createQR(List<Product> products) throws Exception {

        BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        var g2d = initImage(bufferedImage);

        var posX = 0;
        var posY = 0;
        var indexRow = 0;
        var indexCol = 0;

        var part = 1;

        for (int i = 0; i < products.size(); i++) {
            var product = products.get(i);


            if (indexCol > col) {
                indexCol = 0;
                indexRow++;
            }

            if (indexRow >= row && indexCol >= col) {
                indexRow = 0;
                indexCol = 0;

                g2d.dispose();
                saveFile(bufferedImage, part);

                bufferedImage = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
                g2d = initImage(bufferedImage);
                part++;
            }


            posX = indexCol * (QR_WIDTH + WIDTH_ITEM);
            posY = indexRow * (QR_HEIGHT + HEIGHT_ITEM);

            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix =
                    barcodeWriter.encode(Integer.toString(product.getId()), BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
            var qrCode =  MatrixToImageWriter.toBufferedImage(bitMatrix);

            g2d.drawImage(qrCode, posX , posY, null);

            var text = product.getId() + " " + product.getName();
            g2d.drawString(text, posX , posY + QR_HEIGHT + 5);
            g2d.drawString(Integer.toString(product.getPrice()), posX , posY + QR_HEIGHT + SPACE);

            if (i == products.size() - 1) {
                g2d.dispose();
                saveFile(bufferedImage, part);
            }
            indexCol++;
        }


    }
    private void saveFile(BufferedImage image, int part) throws Exception {
        LocalDate now = LocalDate.now();
        var fileName = "QR_part_" + part + "." + now + ".png";
        File file = new File(fileName);
        ImageIO.write(image, "png", file);
    }

    private Graphics2D initImage(BufferedImage bufferedImage){
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setFont(new Font("Segoe Script" , Font.PLAIN, 20));
        g2d.setPaint(Color.BLACK);
        return g2d;
    }


}
