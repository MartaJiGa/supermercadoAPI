package com.svalero.supermercadoAPI.repository;

import com.svalero.supermercadoAPI.domain.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    List<Purchase> findAll();
    List<Purchase> findByUser(long userId);
    Optional<Purchase> findByUserAndId(long userId, long id);
}
