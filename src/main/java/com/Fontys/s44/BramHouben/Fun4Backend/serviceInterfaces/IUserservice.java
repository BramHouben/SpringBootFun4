package com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces;

import com.Fontys.s44.BramHouben.Fun4Backend.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserservice {

    /**
     * @param user
     * @return
     */
    boolean insertUser(User user);

    /**
     * @param user
     * @return
     */


    /**
     * @return
     */
    List<User> getAllUsers();
    /**
     * @param uuid
     * @return
     */
    Optional<User> selectUserById(UUID uuid);

    /**

     */
    void deleteUser(String username);

    /**

     * @return
     */
    boolean updateUser(User user, String name );

//    User findByUserName(String username);
}
