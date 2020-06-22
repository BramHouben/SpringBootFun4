package com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces;

import com.Fontys.s44.BramHouben.Fun4Backend.model.*;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();

    void sendOrder(List<productOrdered> products, Authentication authentication);

    List<Order> getOrdersUser(String name);

    OrderDetailsView getDetailsOrder(String name, Long id);
}
