package com.svalero.supermercadoAPI.controller;

import com.svalero.supermercadoAPI.domain.Purchase;
import com.svalero.supermercadoAPI.exception.UserNotFoundException;
import com.svalero.supermercadoAPI.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    //region GET requests
    @GetMapping("/purchase/{purchaseId}")
    public Optional<Purchase> getUserPurchaseById(@PathVariable long purchaseId){
        return purchaseService.getPurchaseById(purchaseId);
    }
    @GetMapping("/user/{userId}/purchases")
    public List<Purchase> getUserPurchase(@PathVariable long userId){
        return purchaseService.getPurchasesByUser(userId);
    }
    //endregion

    //region POST requests
    @PostMapping("/user/{userId}/purchases")
    public void savePurchase(@RequestBody Purchase purchase, @PathVariable long userId) throws UserNotFoundException {
        purchaseService.savePurchase(purchase, userId);
    }
    //endregion

    //region PUT requests
    @PutMapping("/purchase/{purchaseId}")
    public void modifyPurchase(@RequestBody Purchase purchase, @PathVariable long purchaseId, @PathVariable long userId){
        purchaseService.modifyPurchase(purchase, purchaseId, userId);
    }
    //endregion

    //region DELETE requests
    @DeleteMapping("/purchase/{purchaseId}")
    public void removePurchase(@PathVariable long purchaseId){
        purchaseService.removePurchase(purchaseId);
    }
    //endregion
}
