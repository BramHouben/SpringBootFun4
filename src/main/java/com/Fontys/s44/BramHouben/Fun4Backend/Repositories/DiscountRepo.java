package com.Fontys.s44.BramHouben.Fun4Backend.Repositories;

import com.Fontys.s44.BramHouben.Fun4Backend.model.OrderDetails;
import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductDiscount;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepo extends CrudRepository<ProductDiscount, Integer> {

    boolean existsByProduct(Product product);
    ProductDiscount getByProduct(Product product);


}
