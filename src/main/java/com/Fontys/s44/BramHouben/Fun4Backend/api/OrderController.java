package com.Fontys.s44.BramHouben.Fun4Backend.api;


import com.Fontys.s44.BramHouben.Fun4Backend.model.*;
import com.Fontys.s44.BramHouben.Fun4Backend.service.AdminService;
import com.Fontys.s44.BramHouben.Fun4Backend.service.OrderService;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IAdminService;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/order")
@RestController
public class OrderController {


    private final IOrderService orderservice;
    private final IAdminService adminService;

    @Autowired
    public OrderController(OrderService orderservice, AdminService adminService) {
        this.orderservice = orderservice;
        this.adminService= adminService;
    }

    @PreAuthorize("hasAuthority('basicuser:write')")
    @PostMapping(path = "/sendOrder", consumes = "application/json")
    public ResponseEntity<?> sendOrder(@Valid @NotNull @RequestBody ProductWrapper products, Authentication authentication) {
    if (authentication==null){
        return ResponseEntity.badRequest().body("not authenticated");
    }
    if (products.getProducts()==null){
        return ResponseEntity.badRequest().body("No products to load");
    }
        orderservice.sendOrder(products.getProducts(), authentication);
        if (adminService.isDiscountNeeded()){
            adminService.startDiscount();
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('basicuser:read')")
    @GetMapping(path = "/ordersUser")
    public ResponseEntity<?> getOrders(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.badRequest().body("not authenticated");
        }

        List<Order> orders = orderservice.getOrdersUser(authentication.getName());
        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.badRequest().body("Can't load products");
        }
    }

    @PreAuthorize("hasAuthority('basicuser:read')")
    @GetMapping(path = "/getDetails")
    public ResponseEntity<?> getDetails( @RequestParam(name = "id")Long id, Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.badRequest().body("not authenticated");
        }
        if (id == null) {
            return ResponseEntity.badRequest().body("not good request");
        }
        OrderDetailsView order = orderservice.getDetailsOrder(authentication.getName(), id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.badRequest().body("can't load products");
        }
    }

    @PreAuthorize("hasAuthority('order:read')")
    @GetMapping()
    public ResponseEntity<?> getAllOrdersPlaced() {
        List<Order> order = orderservice.getAllOrders();
        if (order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.badRequest().body("can't load Orders");
        }
    }
}
