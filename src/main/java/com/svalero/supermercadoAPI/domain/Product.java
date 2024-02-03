package com.svalero.supermercadoAPI.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private float price;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column
    private String description;

    @ManyToMany
    @JoinColumn(name = "purchase_id")
    private Optional<Purchase> purchase;
}
