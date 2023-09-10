package com.example.demo.entity;
import javax.persistence.*;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;
    private String brand; // New attribute for the brand

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Constructors, getters, and setters

    public Product() {
        // Default constructor
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters for id, name, and price

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
