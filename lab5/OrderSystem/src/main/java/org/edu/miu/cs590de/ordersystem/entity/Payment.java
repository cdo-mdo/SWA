package org.edu.miu.cs590de.ordersystem.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @OneToOne
    private Order order;

}
