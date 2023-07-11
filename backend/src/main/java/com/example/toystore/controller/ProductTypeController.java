package com.example.toystore.controller;

import com.example.toystore.dto.ProductType;
import com.example.toystore.dto.Response;
import com.example.toystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class ProductTypeController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public Response<Integer> createProductType(@RequestBody ProductType type){
        var res = productService.insertTypeProduct(type);
        return Response.<Integer> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
    @GetMapping("")
    public Response<List<ProductType>> getAllProductType(){
        var res = productService.getAllProductType();
        return Response.<List<ProductType>> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }

    @GetMapping("/{id}")
    public Response<ProductType> getProductTypeById(@PathVariable int id){
        var res = productService.getProductTypeById(id);
        return Response.<ProductType> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }

    @PutMapping("")
    public Response<ProductType> updateProductType(@RequestBody ProductType type) {
        var res = productService.updateProductType(type);
        return Response.<ProductType>builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteProductType(@PathVariable int id) {
        var res = productService.deleteProductType(id);
        return Response.<Boolean>builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
}
