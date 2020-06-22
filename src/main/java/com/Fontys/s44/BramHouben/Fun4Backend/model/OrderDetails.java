package com.Fontys.s44.BramHouben.Fun4Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
public class OrderDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

 //   @JoinColumn(name = "Orders.orderdetailsid", nullable = false)
//    @Column(name = "orderdetailsid", columnDefinition = "BINARY(16)")
//    private UUID orderDetailsId;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Orders.orderdetailsid", nullable = false)
  //  @Column(name = "orderdetailsid", columnDefinition = "BINARY(16)")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Order order ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Products.UUID", nullable = false)
    @JsonIgnore
    private Product product ;

    private int count;
    public OrderDetails(){}

    public OrderDetails(Long id) {
        this.id = id;
    }

    /**
     * Dit is voor de bestelling
     * @param order
     * @param product
     */
    public OrderDetails(Order order, Product product,int count) {
        this.order = order;
        this.product=product;
        this.count = count;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", order=" + order +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
