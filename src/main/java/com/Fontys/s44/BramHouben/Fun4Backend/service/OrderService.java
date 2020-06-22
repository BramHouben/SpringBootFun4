package com.Fontys.s44.BramHouben.Fun4Backend.service;


import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.OrderDetailsRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.OrderRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.UserRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.model.*;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IOrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final OrderDetailsRepo orderDetailsRepo;

    public OrderService(OrderRepo orderRepo, UserRepo userRepo, OrderDetailsRepo orderDetailsRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.orderDetailsRepo = orderDetailsRepo;
    }


    @Override
    public List<Order> getAllOrders() {
        Iterable<Order> allOrders = orderRepo.findAll();
        List<Order> orders = new ArrayList<>();
        allOrders.forEach(p -> orders.add(p));

        return orders;
    }


    @Override
    public void sendOrder(List<productOrdered> products, Authentication authentication) {
        double totalPrice = 0;
        UUID generateUUIDOrder = UUID.randomUUID();
        User user = userRepo.getUserByName(authentication.getName());
        for (productOrdered product : products
        ) {
            double priceCount;
            if (product.getDiscount() >= 1) {
                priceCount = product.getDiscount() * product.getCount();
            } else {
                priceCount = product.getProductPrice() * product.getCount();
            }
            totalPrice += priceCount;
        }
        Order order = new Order(user.getUserId(), generateUUIDOrder, LocalDateTime.now(), totalPrice);
        orderRepo.save(order);

        for (productOrdered product : products
        ) {
            Product dbProduct = new Product(product.getId());
            OrderDetails orderDetails = new OrderDetails(order, dbProduct, product.getCount());
            orderDetailsRepo.save(orderDetails);
        }


    }

    @Override
    public List<Order> getOrdersUser(String name) {
        User user = userRepo.getUserByName(name);
        return orderRepo.getAllByUserId(user.getUserId());
    }

    @Override
    public OrderDetailsView getDetailsOrder(String name, Long id) {
        User user = userRepo.getUserByName(name);
        List<Order> orders = orderRepo.getAllByUserId(user.getUserId());
        for (Order order : orders
        ) {
            if (order.getId().equals(id)) {
                List<productOrdered> allProductsOrderedUser = new ArrayList<>();
                List<OrderDetails> orders2 = orderDetailsRepo.getAllByOrder(order);
                for (OrderDetails ordersdetails : orders2
                ) {
                    Product product = ordersdetails.getProduct();
                    int count = ordersdetails.getCount();
                    productOrdered productOrdered = new productOrdered(product.getId(), product.getProductName(), product.getPrice(), count, 0);
                    allProductsOrderedUser.add(productOrdered);


                }
                double endPrice = (double) Math.round(order.getTotalPrice()* 100)/100;

                return new OrderDetailsView(endPrice, allProductsOrderedUser);


            }
        }

        return new OrderDetailsView();
    }

}
