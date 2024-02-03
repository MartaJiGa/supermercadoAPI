package com.svalero.supermercadoAPI.service;

import com.svalero.supermercadoAPI.domain.Purchase;
import com.svalero.supermercadoAPI.domain.User;
import com.svalero.supermercadoAPI.exception.ProductNotFoundException;
import com.svalero.supermercadoAPI.exception.UserNotFoundException;
import com.svalero.supermercadoAPI.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserService userService;

    //region GET requests
    public List<Purchase> getPurchases(){
        return purchaseRepository.findAll();
    }
    public Purchase getPurchaseById(Long id) throws ProductNotFoundException {
        return purchaseRepository.findById(id).orElseThrow(()-> new ProductNotFoundException());
    }
    public List<Purchase> getPurchasesByUser(long id){
        return purchaseRepository.findByUser(id);
    }
    //endregion

    //region POST requests
    public void addPurchase(Purchase purchase, long userId) throws UserNotFoundException {
        User user = userService.getUserById(userId).orElseThrow(()-> new UserNotFoundException());
        purchase.setUser(user);
        purchaseRepository.save(purchase);
    }
    //endregion

    //region PUT requests
    public void modifyPurchase(Purchase newPurchase, long purchaseId){
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);

        if(purchase.isPresent()){
            Purchase existingPurchase = purchase.get();

            existingPurchase.setDelayedPayment(newPurchase.isDelayedPayment());
            existingPurchase.setNotes(newPurchase.getNotes());
        }
    }
    //endregion

    //region DELETE requests
    public void removePurchase(long purchaseId){
        purchaseRepository.deleteById(purchaseId);
    }
    //endregion
}
