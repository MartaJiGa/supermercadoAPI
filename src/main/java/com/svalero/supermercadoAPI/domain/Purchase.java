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

@Entity(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "delayed_payment")
    private boolean delayedPayment;

    @Column
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Optional<User> user;

    @ManyToMany
    @JoinColumn(name = "product_id")
    private Optional<Product> product;
}