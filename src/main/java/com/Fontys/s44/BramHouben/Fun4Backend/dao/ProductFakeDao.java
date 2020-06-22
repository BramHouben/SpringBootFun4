package com.Fontys.s44.BramHouben.Fun4Backend.dao;


import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductViewModel;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IProductService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("ProductFakeDao")
public class ProductFakeDao implements IProductService {

    private static final List<ProductViewModel> products = new ArrayList<>();


    @Override
    public boolean insertProduct(Product product) {
        if (product==null ||product.getId()==null){
            return false;
        }
        ProductViewModel productViewModel = new ProductViewModel(product.getId(),product.getProductName(),product.getPrice(),product.getPicture());
        products.add(productViewModel);
        return true;
    }

    @Override
    public void editProductById(Product product) {

    }

    @Override
    public boolean deleteProductById(UUID uuid) {
        return false;
    }

    @Override
    public List<ProductViewModel> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(UUID uuid) {
        return null;
    }

    @Override
    public void savePhoto(String name, String pictureName) {

    }
}
