package com.Fontys.s44.BramHouben.Fun4Backend;


import com.Fontys.s44.BramHouben.Fun4Backend.dao.ProductFakeDao;
import com.Fontys.s44.BramHouben.Fun4Backend.dao.UserFakeDao;
import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import com.Fontys.s44.BramHouben.Fun4Backend.model.ProductViewModel;
import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ProductTest {


    @Autowired
    private ProductFakeDao productFakeDao;

    @Test
    void contextLoads() {
    }

    @Test
    void addProductNull() {
        Product product = new Product();

        boolean action = productFakeDao.insertProduct(product);

        List<ProductViewModel> productList = productFakeDao.getAllProducts();

        // Assert.assertNotNull(productFakeDao.getAllProducts());

        Assert.assertEquals(0, productList.size());
        Assert.assertFalse(action);
    }

    @Test
    void addProduct() {
        Product product = new Product(UUID.randomUUID(), "TestName", (double) 10, "");

        boolean action = productFakeDao.insertProduct(product);

        List<ProductViewModel> productList = productFakeDao.getAllProducts();

        Assert.assertNotNull(productFakeDao.getAllProducts());
        Assert.assertEquals(1, productList.size());
        Assert.assertTrue(action);
    }
}
