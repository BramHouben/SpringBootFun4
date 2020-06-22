package com.Fontys.s44.BramHouben.Fun4Backend.service;

import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.DiscountRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.OrderDetailsRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.ProductRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.model.*;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class AdminService implements IAdminService {

    private final ProductRepo productRepo;
    private final DiscountRepo discountRepo;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderDetailsRepo orderDetailsRepo;

    public AdminService(ProductRepo productRepo, DiscountRepo discountRepo, ProductService productService, OrderService orderService, OrderDetailsRepo orderDetailsRepo) {
        this.productRepo = productRepo;
        this.discountRepo = discountRepo;
        this.productService = productService;
        this.orderService = orderService;
        this.orderDetailsRepo = orderDetailsRepo;
    }

    @Override
    public void addDiscount(UUID id, double newPrice) {
        Product product = productRepo.getProductById(id);
        ProductDiscount productDiscount = new ProductDiscount(product, newPrice);
        discountRepo.save(productDiscount);

    }


    @Override
    public void startDiscount() {
        discountRepo.deleteAll();
        List<Algorithm> algorithms = new ArrayList<>();
        List<ProductViewModel> products = productService.getAllProducts();
        List<Order> orders = orderService.getAllOrders();
        double countProducts = products.size();

        List<OrderDetails> orderDetails = (List<OrderDetails>) orderDetailsRepo.findAll();
        for (OrderDetails orderDetail : orderDetails
        ) {
            boolean added = false;
            if (algorithms.isEmpty()) {
                algorithms.add(new Algorithm(orderDetail.getCount(), orderDetail.getProduct()));
                continue;
            }
            for (Algorithm algo : algorithms
            ) {
                if (algo.getProduct() == orderDetail.getProduct()) {
                    algo.setCount(algo.getCount() + orderDetail.getCount());
                    added = true;
                    break;
                }
            }
            if (!added) {
                algorithms.add(new Algorithm(orderDetail.getCount(), orderDetail.getProduct()));
            }

        }

        for (Algorithm algorithm : algorithms
        ) {


//            if (algorithm.getCount() >= countProducts) {
//                continue;
//            }
            //Lager als 1/5 van aantal bestellingen


            double minimalBought = countProducts/ algorithm.getCount() ;

            if (algorithm.getCount() >= minimalBought) {
                continue;
            }



            Product productNormal = productRepo.getProductById(algorithm.getProduct().getId());
            Product productAlgorithm = algorithm.getProduct();

            double normalPrice = productNormal.getPrice();

            double formula = 50 / (double) algorithm.getCount();

            if (formula <= 10) {
                continue;
            }


            double discount = (100 - formula) / 100;

            double priceAfterDiscount = normalPrice * discount;

            double endPrice = (double) Math.round(priceAfterDiscount * 100) / 100;

            ProductDiscount productDiscount = new ProductDiscount(productAlgorithm, endPrice);


            discountRepo.save(productDiscount);


        }


    }

    @Override
    public boolean isDiscountNeeded() {
        return orderDetailsRepo.count() % 5 == 0;

    }
}
