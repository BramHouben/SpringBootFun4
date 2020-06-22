package com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces;

import java.util.UUID;

public interface IAdminService {


    /**
     * Starts the discount process for the products
     */
    void startDiscount();

    /**
     * @return true if devised by 10 and false if not
     */
    boolean isDiscountNeeded();

    void addDiscount(UUID uuid, double newPrice);
}
