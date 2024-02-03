package com.svalero.supermercadoAPI.controller;

import com.svalero.supermercadoAPI.domain.ErrorResponse;
import com.svalero.supermercadoAPI.domain.Purchase;
import com.svalero.supermercadoAPI.exception.ProductNotFoundException;
import com.svalero.supermercadoAPI.exception.UserNotFoundException;
import com.svalero.supermercadoAPI.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    //region GET requests
    @GetMapping("/purchases")
    public List<Purchase> getPurchases() {
        return purchaseService.getPurchases();
    }
    @GetMapping("/purchase/{purchaseId}")
    public Purchase getUserPurchaseById(@PathVariable long purchaseId) throws ProductNotFoundException {
        logger.info("ini GET /product/" + purchaseId);
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        logger.info("end GET /product/" + purchaseId);
        return purchase;
    }
    @GetMapping("/user/{userId}/purchases")
    public List<Purchase> getUserPurchase(@PathVariable long userId) throws UserNotFoundException {
        return purchaseService.getPurchasesByUser(userId);
    }
    //endregion

    //region POST requests
    @PostMapping("/user/{userId}/purchases")
    public void addPurchase(@RequestBody Purchase purchase, @PathVariable long userId) throws UserNotFoundException {
        purchaseService.addPurchase(purchase, userId);
    }
    //endregion

    //region PUT requests
    @PutMapping("/purchase/{purchaseId}")
    public void modifyPurchase(@RequestBody Purchase purchase, @PathVariable long purchaseId) throws ProductNotFoundException {
        purchaseService.modifyPurchase(purchase, purchaseId);
    }
    //endregion

    //region DELETE requests
    @DeleteMapping("/purchase/{purchaseId}")
    public void removePurchase(@PathVariable long purchaseId) throws ProductNotFoundException {
        purchaseService.removePurchase(purchaseId);
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
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException userNotFoundEx){
        ErrorResponse errorResponse = new ErrorResponse(404, userNotFoundEx.getMessage());
        logger.error(userNotFoundEx.getMessage(), userNotFoundEx);
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
