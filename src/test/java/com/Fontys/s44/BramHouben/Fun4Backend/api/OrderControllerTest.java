package com.Fontys.s44.BramHouben.Fun4Backend.api;

import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.OrderDetailsRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.OrderRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.UserRepo;

import com.Fontys.s44.BramHouben.Fun4Backend.model.Order;
import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductWrapper;
import com.Fontys.s44.BramHouben.Fun4Backend.model.productOrdered;
import com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles;
import com.Fontys.s44.BramHouben.Fun4Backend.service.AdminService;
import com.Fontys.s44.BramHouben.Fun4Backend.service.OrderService;

import io.jsonwebtoken.lang.Assert;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
class OrderControllerTest {

    private Authentication authentication;

    @Mock
    private OrderDetailsRepo orderDetailsRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private OrderRepo orderRepo;

    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @InjectMocks
    private OrderService orderService;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    public void Setup() throws Exception{
        orderService = new OrderService(orderRepo,userRepo,orderDetailsRepo);
        orderController = new OrderController(orderService, adminService);
   //     authentication= setAuthentication();
        mockMvc = MockMvcBuilders.
                standaloneSetup(orderController).build();
    }


    @Test
    void getOrders() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order",authentication)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getOrdersWithoutAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orer"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
    @Test
    void getOrdersDetailsWrong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/order/ordersUser")).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }




}

