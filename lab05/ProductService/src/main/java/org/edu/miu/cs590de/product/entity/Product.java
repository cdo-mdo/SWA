package org.edu.miu.cs590de.product.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    @Id
    private String id;

    private String name;
    private String description;
    private double price;
    private int quantity;
}
