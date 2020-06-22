package com.Fontys.s44.BramHouben.Fun4Backend.service;


import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.DiscountRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.ProductRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductDiscount;
import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductViewModel;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IProductService;
import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class ProductService implements IProductService {
    Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepo productRepo;
    private final DiscountRepo discountRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, DiscountRepo discountRepo) {
        this.productRepo = productRepo;
        this.discountRepo = discountRepo;
    }

    @Override
    public boolean insertProduct(Product product) {
        if (product.getPrice() == null || product.getProductName() == null) {
            return false;
        }

        try {
            productRepo.save(product);

        } catch (Exception exception) {
            logger.error(exception.toString());
        }
        return true;

    }

    @Override
    public void editProductById(Product product) {
        Product productChanged = productRepo.getProductById(product.getId());
        productChanged.setPrice(product.getPrice());
        productChanged.setProductName(product.getProductName());
        productRepo.save(productChanged);
    }



    @Override
    public List<ProductViewModel> getAllProducts() {
        Iterable<Product> allProducts = productRepo.findAll();
        List<ProductViewModel> products = new ArrayList<>();
        for (Product product : allProducts
        ) {
            if (!discountRepo.existsByProduct(product)) {
                ProductViewModel productViewModel = new ProductViewModel(product.getId(), product.getProductName(), product.getPrice(), product.getPicture());
                products.add(productViewModel);
            } else {
                ProductDiscount productDiscount = discountRepo.getByProduct(product);
                ProductViewModel productViewModel = new ProductViewModel(product.getId(), product.getProductName(), product.getPrice(), product.getPicture(), productDiscount.getPriceDiscount());
                products.add(productViewModel);
            }

        }



        return products;
    }

    @Override
    public Product getProductById(UUID uuid) {
        return productRepo.getProductById(uuid);
    }

    @Override
    public void savePhoto(String name, String pictureName) {
        Product product = productRepo.getProductByproductName(pictureName);
        String uri= "http://localhost:8095/api/v1/picture/downloadFile/"+name;
        product.setPicture(uri);
    }

    @Override
    public boolean deleteProductById(UUID uuid) {
        long beforeDelete = productRepo.count();
        try {
            productRepo.deleteById(uuid);
        } catch (Exception exception) {
            logger.error(exception.toString());
            return false;
        }

        return productRepo.count() != beforeDelete;
    }

}
