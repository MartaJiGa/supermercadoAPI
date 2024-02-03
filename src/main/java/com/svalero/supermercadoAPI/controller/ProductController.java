package com.svalero.supermercadoAPI.controller;

import com.svalero.supermercadoAPI.domain.ErrorResponse;
import com.svalero.supermercadoAPI.domain.Product;
import com.svalero.supermercadoAPI.exception.ProductNotFoundException;
import com.svalero.supermercadoAPI.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    //region GET requests
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable long productId) throws ProductNotFoundException {
        logger.info("ini GET /product/" + productId);
        Product product = productService.getProductById(productId);
        logger.info("end GET /product/" + productId);
        return product;
    }
    @GetMapping("/products")
    public List<Product> findAll(@RequestParam(defaultValue = "")String name, @RequestParam(defaultValue = "0") float price){
        if(!name.isEmpty() && price == 0){
            return productService.findByName(name);
        }
        else if(name.isEmpty() && price != 0){
            return productService.findByPrice(price);
        }
        else if(!name.isEmpty() && price != 0){
            return productService.findByNameAndPrice(name, price);
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
        logger.error(badRequestEx.getMessage(), badRequestEx);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundException(ProductNotFoundException resNotFoundEx){
        ErrorResponse errorResponse = new ErrorResponse(404, resNotFoundEx.getMessage());
        logger.error(resNotFoundEx.getMessage(), resNotFoundEx);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> internalServerError(HttpServerErrorException.InternalServerError intServError){
        ErrorResponse errorResponse = new ErrorResponse(500, intServError.getMessage());
        logger.error(intServError.getMessage(), intServError);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //endregion
}
