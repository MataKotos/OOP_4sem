package com.omgtu.model;

public class Bouquet {
    private String name;
    private String size;
    private double price;
    private String description;
    private String status;

    public Bouquet() {}

    public Bouquet(String name, String size, double price, String description, String status) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    // геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

