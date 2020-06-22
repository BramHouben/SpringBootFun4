package com.Fontys.s44.BramHouben.Fun4Backend.model;

import java.io.Serializable;
import java.util.List;

public class ProductWrapper implements Serializable {

   private List<productOrdered>products;

    public List<productOrdered> getProducts() {
        return products;
    }

    public void setProducts(List<productOrdered> products) {
        this.products = products;
    }


}
