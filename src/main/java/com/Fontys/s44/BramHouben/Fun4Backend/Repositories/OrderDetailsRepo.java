package com.Fontys.s44.BramHouben.Fun4Backend.Repositories;

import com.Fontys.s44.BramHouben.Fun4Backend.model.Order;
import com.Fontys.s44.BramHouben.Fun4Backend.model.OrderDetails;
import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailsRepo extends CrudRepository<OrderDetails, Long> {

    long countAllByProduct(Product product);

    List<OrderDetails> getAllByOrder(Order order);
}
