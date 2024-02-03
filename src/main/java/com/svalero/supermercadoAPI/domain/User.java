package com.svalero.supermercadoAPI.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column
    private String email;

    @OneToMany
    @JoinColumn(name = "purchase_id")
    private List<Purchase> purchase;
}