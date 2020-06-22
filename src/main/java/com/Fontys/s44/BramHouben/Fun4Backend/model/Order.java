package com.Fontys.s44.BramHouben.Fun4Backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "useraccount.uuid", nullable = false)
    @Column(name = "UUID", columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "orderdetailsid", columnDefinition = "BINARY(16)")
    private UUID orderDetailsId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeBought;

    private double totalPrice;

    public Order(){}


    public Order(UUID userId, UUID orderDetailsId, LocalDateTime timeBought,double totalPrice) {
        this.userId = userId;
        this.orderDetailsId = orderDetailsId;
        this.timeBought = timeBought;
        this.totalPrice= totalPrice;
    }

    public Order(Long id, UUID userId, UUID orderDetailsId, LocalDateTime timeBought,double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderDetailsId = orderDetailsId;
        this.timeBought = timeBought;
        this.totalPrice= totalPrice;

    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDetailsId=" + orderDetailsId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(UUID orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public LocalDateTime getTimeBought() {
        return timeBought;
    }

    public void setTimeBought(LocalDateTime timeBought) {
        this.timeBought = timeBought;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
