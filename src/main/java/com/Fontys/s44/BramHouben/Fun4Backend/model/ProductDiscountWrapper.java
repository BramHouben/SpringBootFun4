package com.Fontys.s44.BramHouben.Fun4Backend.model;

import java.util.UUID;

public class ProductDiscountWrapper {

    private UUID uuid;
    private double newPrice;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
