package org.edu.miu.cs590de.ordersystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;
    private String email;

    @OneToOne
    private Address address;

    @OneToMany
    private List<Product> products;
}
