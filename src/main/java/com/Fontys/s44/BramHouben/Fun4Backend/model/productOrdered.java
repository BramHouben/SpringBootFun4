package com.Fontys.s44.BramHouben.Fun4Backend.model;

import java.util.UUID;

public class productOrdered {

    private UUID id;

    private String productName;
    private Double productPrice;
    private Double discount;
    private int count;


//    public productOrdered(UUID id, String productName, Double price, int count) {
//        this.id = id;
//        this.productName = productName;
//        this.productPrice = price;
//        this.count = count;
//    }
    public productOrdered(UUID id, String productName, Double price, int count, double discount) {
        this.id = id;
        this.productName = productName;
        this.productPrice = price;
        this.discount= discount;
        this.count = count;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }




    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
