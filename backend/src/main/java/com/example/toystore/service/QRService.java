package com.example.toystore.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


@Service
public class QRService {
    public BufferedImage createQR(String text) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
        var QRimage =  MatrixToImageWriter.toBufferedImage(bitMatrix);

        File outputfile = new File("image.jpg");
        ImageIO.write(QRimage, "jpg", outputfile);

        return null;

    }
}
