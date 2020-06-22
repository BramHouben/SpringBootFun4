package com.Fontys.s44.BramHouben.Fun4Backend.service;

import com.Fontys.s44.BramHouben.Fun4Backend.Repositories.UserRepo;
import com.Fontys.s44.BramHouben.Fun4Backend.authenticate.ApplicationUserDao;
import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles.BASICUSER;

@Service
@Transactional
public class UserService implements IUserservice {


    private final UserRepo userRepo;

    @Autowired
    private ApplicationUserDao applicationUserDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean insertUser(User user) {
        // check of user already exist
        if (user.getName()==null){
            return false;
        }

        if (userRepo.getUserByName(user.getName())!=null){
            return false;
        }else{
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRoles(BASICUSER);
            userRepo.save(user);
            applicationUserDao.addUserToAfterRegister(user);
            return true;
        }

    }

    @Override
    public List<User> getAllUsers() {
        Iterable<User> allUsers = userRepo.findAll();
        List<User> users = new ArrayList<>();
        allUsers.forEach(u -> users.add(u));

        return users;
    }

    @Override
    public Optional<User> selectUserById(UUID uuid) {
        User user = userRepo.getUserByUserId(uuid);
        if (user != null) {
            return Optional.ofNullable(user);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(String username) {
        userRepo.deleteByName(username);
    }

    @Override
    public boolean updateUser(User user, String name) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        User userUpdate = userRepo.getUserByName(name);
        userUpdate.setPassword(encodedPassword);
        userRepo.save(userUpdate);
        return true;
// todo verander dit in applicationUserDao

    }
}
