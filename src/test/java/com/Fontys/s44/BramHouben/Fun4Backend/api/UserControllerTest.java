package com.Fontys.s44.BramHouben.Fun4Backend.api;

import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.ProductRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.UserRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.authenticate.ApplicationUserDao;
import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import com.Fontys.s44.BramHouben.Fun4Backend.security.ApplicationPermissions;
import com.Fontys.s44.BramHouben.Fun4Backend.service.OrderService;
import com.Fontys.s44.BramHouben.Fun4Backend.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    private Authentication authentication;


    @Mock
    private UserRepo userRepo;

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @InjectMocks
    private UserService userService;


    private Authentication  setAuthentication(){
        authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
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
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "user";
            }
        };
        return authentication;
    }

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepo);
        userController = new UserController(userService);

        mockMvc = MockMvcBuilders.
                standaloneSetup(userController).build();
    }

    @Test
    void addUser() {
      long count = userRepo.count();
       User user = new User(UUID.randomUUID(),"test",12,"Male","pass");
      // userRepo.save(user);
        Assertions.assertDoesNotThrow(()->userRepo.save(user));
//       boolean insertUser = userService.insertUser(user);
//        Assert.assertTrue(insertUser);

        User user2 = new User(UUID.randomUUID(),null,12,"Male","pass");
        boolean insertUser2 = userService.insertUser(user2);
        Assert.assertFalse(insertUser2);


    }

//    @Test
//    void getAllUsers() {
//        mockMvc.perform("")
//        User user = new User(UUID.randomUUID(),"test",12,"Male","pass");
//        userController.addUser(user);
//        List<User> userList= userController.getAllUsers();
//        Assertions.assertNotNull(userList);
//
//    }

    @Test
    void getUsernameCookie() {
    }

    @Test
    void getUserById() {
        User user = new User(UUID.randomUUID(),"test",12,"Male","pass");
        when(userRepo.save(user)).thenReturn(user);
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

}