package com.Fontys.s44.BramHouben.Fun4Backend.model;

import java.util.List;

public class OrderDetailsView {

    private double totalPrice;
    private List<productOrdered> productOrderedList;

    @Override
    public String toString() {
        return "OrderDetailsView{" +
                "totalPrice=" + totalPrice +
                ", productOrderedList=" + productOrderedList +
                '}';
    }

    public OrderDetailsView(){

}

    public OrderDetailsView(double totalPrice, List<productOrdered> productOrderedList) {
        this.totalPrice = totalPrice;
        this.productOrderedList = productOrderedList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<productOrdered> getProductOrderedList() {
        return productOrderedList;
    }

    public void setProductOrderedList(List<productOrdered> productOrderedList) {
        this.productOrderedList = productOrderedList;
    }
}
