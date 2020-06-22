package com.Fontys.s44.BramHouben.Fun4Backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Products")
public class Product implements Serializable {


    @Id
    @GeneratedValue
    @Column(name = "UUID", columnDefinition = "BINARY(16)")
    private UUID id;

    private String productName;
    private Double price;
    private String picture;

    public Product() {

    }

    public Product(@JsonProperty("productId") UUID productId, @JsonProperty("productName") String productName, @JsonProperty("productPrice") Double price,String picture) {
        this.id = productId;
        this.productName = productName;
        this.price = price;
        this.picture=picture;
    }

    public Product(UUID id) {
            this.id=id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
