package com.svalero.supermercadoAPI.service;

import com.svalero.supermercadoAPI.domain.Purchase;
import com.svalero.supermercadoAPI.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    //region GET requests
    public List<Purchase> getPurchases(){
        return purchaseRepository.findAll();
    }
    public Optional<Purchase> getPurchaseById(Long id){
        return purchaseRepository.findById(id);
    }
    //endregion

    //region POST requests
    public void savePurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }
    //endregion

    //region PUT requests
    //endregion

    //region DELETE requests
    public void removePurchase(long purchaseId){
        purchaseRepository.deleteById(purchaseId);
    }
    //endregion
}
