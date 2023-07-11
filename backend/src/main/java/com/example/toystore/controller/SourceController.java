package com.example.toystore.controller;

import com.example.toystore.dto.Response;
import com.example.toystore.dto.Source;
import com.example.toystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/source")
public class SourceController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public Response<Integer> createProductSource(@RequestBody Source source){
        var res = productService.insertProductSource(source);
        return Response.<Integer> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
    @GetMapping("")
    public Response<List<Source>> getAllProductSource(){
        var res = productService.getAllProductSource();
        return Response.<List<Source>> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
    @GetMapping("/{id}")
    public Response<Source> getProductSourceById(@PathVariable int id){
        var res = productService.getProductSourceById(id);
        return Response.<Source> builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
    @PutMapping("")
    public Response<Source> updateProductSource(@RequestBody Source source) {
        var res = productService.updateProductSource(source);
        return Response.<Source>builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
    @DeleteMapping("/{id}")
    public Response<Boolean> deleteProductSource(@PathVariable int id) {
        var res = productService.deleteProductSource(id);
        return Response.<Boolean>builder()
                .code(200)
                .message("Success")
                .data(res)
                .build();
    }
}
