package com.svalero.supermercadoAPI.controller;

import com.svalero.supermercadoAPI.domain.ErrorResponse;
import com.svalero.supermercadoAPI.domain.Product;
import com.svalero.supermercadoAPI.exception.ProductNotFoundException;
import com.svalero.supermercadoAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //region GET requests
    @GetMapping("/product/{productId}")
    public Optional<Product> getProduct(@PathVariable long productId) throws ProductNotFoundException {
        return productService.getProductById(productId);
    }
    @GetMapping("/products")
    public List<Product> findAll(@RequestParam(defaultValue = "")String productName, @RequestParam(defaultValue = "0") float price){
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
    public void saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }
    //endregion

    //region PUT requests
    @PutMapping("/product/{productId}")
    public void modifyProduct(@RequestBody Product product, @PathVariable long productId) throws ProductNotFoundException {
        productService.modifyProduct(product, productId);
    }
    //endregion

    //region DELETE requests
    @DeleteMapping("/product/{productId}")
    public void removeProduct(@PathVariable long productId) throws ProductNotFoundException {
        productService.removeProduct(productId);
    }
    //endregion

    //region EXCEPTION HANDLER
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> badRequestException(MethodArgumentNotValidException badRequestEx){
        ErrorResponse errorResponse = new ErrorResponse(400, badRequestEx.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundException(ProductNotFoundException resNotFoundEx){
        ErrorResponse errorResponse = new ErrorResponse(404, resNotFoundEx.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> internalServerError(HttpServerErrorException.InternalServerError intServError){
        ErrorResponse errorResponse = new ErrorResponse(500, intServError.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //endregion
}
