package org.edu.miu.cs590de.ordersystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    private Long id;

    private String productName;
    private String description;
    private double price;
}
