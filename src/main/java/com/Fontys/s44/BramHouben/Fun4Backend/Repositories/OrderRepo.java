package com.Fontys.s44.BramHouben.Fun4Backend.Repositories;

import com.Fontys.s44.BramHouben.Fun4Backend.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {

    Order getOrderByuserId(UUID id);
    List<Order>getAllByUserId(UUID id);
}
