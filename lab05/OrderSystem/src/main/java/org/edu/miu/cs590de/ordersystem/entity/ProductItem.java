package org.edu.miu.cs590de.ordersystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    List<Product> products;
}
