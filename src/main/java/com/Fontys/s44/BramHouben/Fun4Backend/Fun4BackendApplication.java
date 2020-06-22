package com.Fontys.s44.BramHouben.Fun4Backend;

import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.OrderRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.ProductRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.UserRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.image.PictureStorageProperties;
import com.Fontys.s44.BramHouben.Fun4Backend.model.Order;
import com.Fontys.s44.BramHouben.Fun4Backend.model.Product;
import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableConfigurationProperties({PictureStorageProperties.class})
public class Fun4BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Fun4BackendApplication.class, args);
    }


    @Bean
    public CommandLineRunner productDemo(ProductRepo productRepo, UserRepo userRepo, OrderRepo orderRepo) {
        return (args) -> {

//            Iterable<User> userList = userRepo.findAll();
//            List<User> users = new ArrayList<>();
//            userList.forEach(u -> users.add(u));
//            User user = users.get(1);
//            Order order = new Order((long) 1,user.getUserId(),UUID.randomUUID());
//        orderRepo.save(order);
//            productRepo.save(new Product(UUID.randomUUID(), "NewProduct", 10.0));
//            productRepo.save(new Product(UUID.randomUUID(), "NewProduct", 10.0));
//            userRepo.save(new User(UUID.randomUUID(), "NewUser", 21, "male", "test"));
//            userRepo.save(new User(UUID.randomUUID(), "NewUser", 19, "female", "test"));
//           String pass = "test";
////
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
//                String   newpass = bCryptPasswordEncoder.encode(pass);
//            userRepo.save(new User(UUID.randomUUID(), "user", 21, "male", newpass, UserRoles.BASICUSER));
//
//            String passadmin = "password";
//
//            String   newpassadmin = bCryptPasswordEncoder.encode(passadmin);
//            userRepo.save(new User(UUID.randomUUID(), "admin", 21, "male", newpassadmin, UserRoles.ADMIN));
//            userRepo.save(new User(UUID.randomUUID(), "NewUser", 19, "female", "test"));

            for (Product product : productRepo.findAll()) {
                System.out.println(product.toString());
            }
//            for (User user : userRepo.findAll()) {
//                System.out.println(user.toString());
//            }

        };
    }
}
