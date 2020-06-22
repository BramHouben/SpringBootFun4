package com.Fontys.s44.BramHouben.Fun4Backend.Repositories;

import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends CrudRepository<Product, UUID> {

    Product getProductByproductName(String name);
    Product getProductById(UUID id);

}
