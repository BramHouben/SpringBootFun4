package com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces;

import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductViewModel;

import java.util.List;
import java.util.UUID;

public interface IProductService {


    boolean insertProduct( Product product);

    void  editProductById(Product product);
    boolean  deleteProductById(UUID uuid);
    List<ProductViewModel> getAllProducts();

    Product getProductById(UUID uuid);

    void savePhoto(String name, String pictureName);
}
