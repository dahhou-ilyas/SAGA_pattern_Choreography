package com.eureka_cloudgateway_openfeign.demo.demo.Entities;


public class Product {

    private Long id;

    private String label;

    private double price;

    private int stock;

    private String description;

    public Product() {
    }

    public Product(Long id, String label, double price, int stock, String description) {
        this.id = id;
        this.label = label;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() { return stock; }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) { this.stock = stock; }

    public void setDescription(String description) {
        this.description = description;
    }

}
