package com.Fontys.s44.BramHouben.Fun4Backend.model;

import java.util.UUID;

public class Algorithm {

   private int count;
    private Product product;

    public Algorithm(){}

    public Algorithm(int count, Product product) {
        this.count = count;
      this.product=product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

