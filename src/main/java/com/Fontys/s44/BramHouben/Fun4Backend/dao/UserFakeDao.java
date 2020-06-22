package com.Fontys.s44.BramHouben.Fun4Backend.dao;

import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IUserservice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("UserFakeDao")
public class UserFakeDao implements IUserservice {

    private static final List<User> userList = new ArrayList<>();


    @Override
    public boolean insertUser(User user) {
        if (user==null||user.getUserId()==null){
        return false;
        }else {
    userList.add(0,user);
    return true;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }



    @Override
    public Optional<User> selectUserById(UUID uuid) {
        return userList.stream().filter(user -> user.getUserId().equals(uuid)).findFirst();
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public boolean updateUser(User user, String name) {
        return false;
    }





//    @Override
//    public User findByUserName(String username) {
//        return null;
//    }
}
