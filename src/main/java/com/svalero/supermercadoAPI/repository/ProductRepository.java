package com.svalero.supermercadoAPI.repository;

import com.svalero.supermercadoAPI.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByPrice(float price);
    List<Product> findByNameAndPrice(String name, float price);
}
