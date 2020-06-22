package com.Fontys.s44.BramHouben.Fun4Backend.authenticate;

import com.Fontys.s44.BramHouben.Fun4Backend.model.User;

import java.util.Optional;

public interface ApplicationUserDao {

     Optional<ApplicationUser> selectApplicationUserByUsername(String username);
     void addUserToAfterRegister(User user);
}
