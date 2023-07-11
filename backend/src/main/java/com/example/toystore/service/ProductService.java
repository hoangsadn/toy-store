package com.example.toystore.service;

import com.example.toystore.dto.Product;
import com.example.toystore.dto.ProductType;
import com.example.toystore.dto.Source;
import com.example.toystore.repository.ProductRepository;
import com.example.toystore.repository.ProductTypeRepository;
import com.example.toystore.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SourceRepository sourceRepository;

    public int insertProduct(Product product){
        return productRepository.insertProduct(product);
    }


    public int insertTypeProduct(ProductType product) {
        return productTypeRepository.insertTypeProduct(product);
    }

    public int insertProductSource(Source source) {
        return sourceRepository.insertProductSource(source);
    }

    public List<Product> getAllProduct() {
        var products =  productRepository.getAllProduct();
        return fillInto(products);
    }

    public List<ProductType> getAllProductType() {
        return productTypeRepository.getAllProductType();
    }

    public List<Source> getAllProductSource() {
        return sourceRepository.getAllProductSource();
    }

    public Product getProductById(int id) {
        var products =  productRepository.getProductById(id);
        return fillInto(products);
    }

    public ProductType getProductTypeById(int id) {
        return productTypeRepository.getProductTypeById(id);
    }

    public Source getProductSourceById(int id) {
        return sourceRepository.getProductSourceById(id);
    }

    private Product fillInto(Product product){
        var type = productTypeRepository.getProductTypeById(product.getTypeId());
        var source = sourceRepository.getProductSourceById(product.getSourceId());
        return product.toBuilder()
                .source(source)
                .type(type)
                .build();
    }
    private List<Product> fillInto(List<Product> products){
        var typesIds = products.stream().map(Product::getTypeId).toList();
        var sourcesIds = products.stream().map(Product::getSourceId).toList();
        var types = productTypeRepository.getProductTypeByIds(typesIds);
        var sources = sourceRepository.getProductSourceByIds(sourcesIds);

        var typesMap = types.stream().collect(Collectors.toMap(ProductType::getId, Function.identity()));
        var sourcesMap = sources.stream().collect(Collectors.toMap(Source::getId, Function.identity()));

        return products.stream().map(product -> product.toBuilder()
                .source(sourcesMap.get(product.getSourceId()))
                .type(typesMap.get(product.getTypeId()))
                .build()).toList();
    }

    public Product updateProduct(Product product) {
        productRepository.updateProduct(product);
        return getProductById(product.getId());
    }

    public Source updateProductSource(Source source) {
        sourceRepository.updateProductSource(source);
        return getProductSourceById(source.getId());
    }

    public ProductType updateProductType(ProductType type) {
        productTypeRepository.updateProductType(type);
        return getProductTypeById(type.getId());
    }

    public Boolean deleteProductSource(int id) {
       return sourceRepository.deleteProductSource(id);
    }

    public boolean deleteProduct(int id) {
       return productRepository.deleteProduct(id);
    }

    public boolean deleteProductType(int id) {
        return productTypeRepository.deleteProductType(id);
    }
}
