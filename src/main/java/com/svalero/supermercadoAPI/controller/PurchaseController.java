package com.svalero.supermercadoAPI.controller;

import com.svalero.supermercadoAPI.domain.Purchase;
import com.svalero.supermercadoAPI.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    //region GET requests
    @GetMapping("/user/{userId}/purchase/{purchaseId}")
    public Optional<Purchase> getUserPurchaseById(@PathVariable long userId, @PathVariable long purchaseId){
        return purchaseService.getPurchaseByUser(userId, purchaseId);
    }
    @GetMapping("/user/{userId}/purchases")
    public List<Purchase> getUserPurchase(@PathVariable long userId){
        return purchaseService.getPurchasesByUser(userId);
    }
    //endregion

    //region POST requests
    //endregion

    //region PUT requests
    //endregion

    //region DELETE requests
    //endregion
}
