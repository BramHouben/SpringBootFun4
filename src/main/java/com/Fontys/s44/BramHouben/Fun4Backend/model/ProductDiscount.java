package com.Fontys.s44.BramHouben.Fun4Backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "discount")
public class ProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int discountId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "products.UUID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    private double priceDiscount;


    @Override
    public String toString() {
        return "ProductDiscount{" +
                "product=" + product +
                ", priceDiscount=" + priceDiscount +
                '}';
    }
    public ProductDiscount(){

    }

    public ProductDiscount(int discountId, Product product, int priceDiscount) {
        this.discountId = discountId;
        this.product = product;
        this.priceDiscount = priceDiscount;
    }
    public ProductDiscount( Product product, double priceDiscount) {

        this.product = product;
        this.priceDiscount = priceDiscount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }
}
