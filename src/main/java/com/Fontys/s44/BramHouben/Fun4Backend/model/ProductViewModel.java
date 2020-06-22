package com.Fontys.s44.BramHouben.Fun4Backend.model;

import java.util.UUID;

public class ProductViewModel {
    private UUID id;

    private String productName;
    private Double price;
    private String picture;
    private Double  discount;


    public ProductViewModel(UUID id, String productName, Double price, String picture) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.picture = picture;

    }

    public ProductViewModel(UUID id, String productName, Double price, String picture, Double  discount) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.picture = picture;
        this.discount = discount;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double  getDiscount() {
        return discount;
    }

    public void setDiscount(Double  discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "ProductViewModel{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", discount=" + discount +
                '}';
    }
}
