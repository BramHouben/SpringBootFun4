package com.Fontys.s44.BramHouben.Fun4Backend.authenticate;


import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.UserRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import com.Fontys.s44.BramHouben.Fun4Backend.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles.ADMIN;
import static com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles.BASICUSER;

@Repository("MysqlUsers")
public class MysqlUserDaoService implements ApplicationUserDao {

    private final List<User> users;
    private final UserService userService;
    private List<ApplicationUser> applicationUsers;

    @Autowired
    public MysqlUserDaoService(UserService userService) {
        this.userService = userService;
        users = userService.getAllUsers();
        setApplicationUser();
    }
    @Override
    public void addUserToAfterRegister(User user){
        Set<SimpleGrantedAuthority> grantedAuthorities = BASICUSER.getGrantedAuthoritySet();

        ApplicationUser normalUser = new ApplicationUser(grantedAuthorities, user.getPassword(), user.getName(), true, true, true, true);
        System.out.println("Application user setted!");
        applicationUsers.add(normalUser);
    }


    private void setApplicationUser() {
        applicationUsers = Lists.newArrayList();
        for (User user : users
        ) {
            if (user.getRoles() == null) {
                //todo dit is ff snelle fix voor null users
                Set<SimpleGrantedAuthority> grantedAuthorities = BASICUSER.getGrantedAuthoritySet();
                ApplicationUser normalUser = new ApplicationUser(grantedAuthorities, user.getPassword(), user.getName(), true, true, true, true);
                applicationUsers.add(normalUser);
            } else if (user.getRoles().name().equals(String.valueOf(ADMIN))) {
                ApplicationUser adminUser = new ApplicationUser(user.getRoles().getGrantedAuthoritySet(), user.getPassword(), user.getName(), true, true, true, true);
                applicationUsers.add(adminUser);
            } else if (user.getRoles().name().equals(String.valueOf(BASICUSER))) {
                ApplicationUser normalUser = new ApplicationUser(user.getRoles().getGrantedAuthoritySet(), user.getPassword(), user.getName(), true, true, true, true);
                applicationUsers.add(normalUser);
            }

        }
    }


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser().stream().filter(applicationUser -> username.equals(applicationUser.getUsername())).findFirst();
    }


    private List<ApplicationUser> getApplicationUser() {

//         List<ApplicationUser> applicationUsers = Lists.newArrayList(
//
//                //todo hier moeten we de lijst van users ophalen vanuit de UserRepo
//
////                new ApplicationUser(ADMIN.getGrantedAuthoritySet(),passwordEncoder.encode("password"),"Admin",true,true,true,true),
////                new ApplicationUser(BASICUSER.getGrantedAuthoritySet(),passwordEncoder.encode("password"),"user",true,true,true,true)
//        );


        return applicationUsers;
    }
}
