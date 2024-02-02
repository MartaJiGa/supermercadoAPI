package com.svalero.supermercadoAPI.service;

import com.svalero.supermercadoAPI.domain.Product;
import com.svalero.supermercadoAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //region GET requests
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
    public List<Product> getProductByName(String name){
        return productRepository.findByName(name);
    }
    public List<Product> getProductByPrice(float price){
        return productRepository.findByPrice(price);
    }
    public List<Product> getProductByNameAndPrice(String name, float price){
        return productRepository.findByNameAndPrice(name,price);
    }
    //endregion

    //region POST requests
    public void saveProduct(Product product){
        productRepository.save(product);
    }
    //endregion

    //region PUT requests
    //endregion

    //region DELETE requests
    //endregion
}