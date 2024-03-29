package com.svalero.supermercadoAPI.service;

import com.svalero.supermercadoAPI.domain.Product;
import com.svalero.supermercadoAPI.exception.ProductNotFoundException;
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
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException());
    }
    public List<Product> findByName(String name){
        return productRepository.findByName(name);
    }
    public List<Product> findByPrice(float price){
        return productRepository.findByPrice(price);
    }
    public List<Product> findByNameAndPrice(String name, float price){
        return productRepository.findByNameAndPrice(name,price);
    }
    //endregion

    //region POST requests
    public void saveProduct(Product product){
        productRepository.save(product);
    }
    //endregion

    //region PUT requests
    public void modifyProduct(Product newProduct, long productId){
        Optional<Product> product = productRepository.findById(productId);

        if(product.isPresent()){
            Product existingProduct = product.get();

            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setDescription(newProduct.getDescription());

            productRepository.save(existingProduct);
        }
    }
    //endregion

    //region DELETE requests
    public void removeProduct(long productId){
        productRepository.deleteById(productId);
    }
    //endregion
}
