package com.svalero.supermercadoAPI.controller;

import com.svalero.supermercadoAPI.domain.Product;
import com.svalero.supermercadoAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //region GET requests
    @GetMapping("/product/{productId}")
    public Optional<Product> getProduct(@PathVariable long productId){
        return productService.getProductById(productId);
    }
    @GetMapping("/products")
    public List<Product> findAll(@RequestParam(defaultValue = "")String productName, @RequestParam(defaultValue = "0")float price){
        if(!productName.isEmpty() && price == 0){
            return productService.getProductByName(productName);
        }
        else if(productName.isEmpty() && price != 0){
            return productService.getProductByPrice(price);
        }
        else if(!productName.isEmpty() && price != 0){
            return productService.getProductByNameAndPrice(productName, price);
        }
        return productService.getProducts();
    }
    //endregion

    //region POST requests
    @PostMapping("/products")
    public void saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
    }
    //endregion

    //region PUT requests
    //endregion

    //region DELETE requests
    //endregion
}
