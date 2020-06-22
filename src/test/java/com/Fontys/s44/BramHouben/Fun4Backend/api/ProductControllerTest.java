package com.Fontys.s44.BramHouben.Fun4Backend.api;

import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.OrderRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.ProductRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles;
import com.Fontys.s44.BramHouben.Fun4Backend.service.OrderService;
import com.Fontys.s44.BramHouben.Fun4Backend.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Mock
    ProductRepo productRepo;

    private MockMvc mockMvc;

    @InjectMocks
    private ProductController productController;
    @InjectMocks
    private FileController fileController;

    @InjectMocks
    private ProductService productService;

    private Authentication authentication;

    @BeforeEach
    public void Setup() throws Exception{

        productController = new ProductController(productService);
        mockMvc = MockMvcBuilders.
                standaloneSetup(productController).build();
        authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return UserRoles.ADMIN.getGrantedAuthoritySet();
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "test";
            }
        };
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/getProducts")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getIndividualProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/getProduct/fe61bf8f-e3b9-494c-ab4e-943e43df75e6")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getIndividualProductWrong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/getProduct/fe61bf8f-e3b9-494c-ab4e-943e43dddf75e6")).
                andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void getProductWrong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/getPoducts")).
                andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
     void postProduct() throws Exception {

        Product product = new Product(UUID.randomUUID(),"test", (double) 15,"");
        productService.insertProduct(product);
        when(productRepo.findById(UUID.randomUUID())).thenReturn(null);
    }

    @Test
    void postProductWrong() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")).
                andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void postProduct2() throws Exception {
        Product product = new Product(UUID.randomUUID(),"test", (double) 15,"");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product",authentication)
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }


    @Test
    void rejectProduct() throws Exception {

        Product product = null;

        Assertions.assertThrows(NullPointerException.class,()->  productService.insertProduct(product) );
    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
