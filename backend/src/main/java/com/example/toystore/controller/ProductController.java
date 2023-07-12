package com.example.toystore.controller;

import com.example.toystore.dto.Product;
import com.example.toystore.dto.Response;
import com.example.toystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public Response<Integer> createProduct(@RequestBody Product product){
        var res =  productService.insertProduct(product);
        return Response.<Integer> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }

    @GetMapping("/all")
    public Response<List<Product>> getAllProduct(){
        var res = productService.getAllProduct();
        return Response.<List<Product>> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }

    @GetMapping("/{id}")
    public Response<Product> getProductById(@PathVariable int id){
        var res = productService.getProductById(id);
        return Response.<Product> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }

    @PutMapping("")
    public Response<Product> updateProduct(@RequestBody Product product) {
        var res = productService.updateProduct(product);
        return Response.<Product>builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
    @DeleteMapping("/{id}")
    public Response<Boolean> deleteProduct(@PathVariable int id) {
        var res = productService.deleteProduct(id);
        return Response.<Boolean>builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }

    @GetMapping("/qr")
    public Response<Boolean> getQR(@RequestBody List<Integer> ids){
        productService.genQRCode(ids);
        return Response.<Boolean>builder()
                .code(200)
                .message("Success")
                .data(true)
                .build();
    }


    @GetMapping("/search")
    public Response<List<Product>> searchProduct(@RequestParam String keyword) {
        var res = productService.searchProduct(keyword);
        return Response.<List<Product>>builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
}
