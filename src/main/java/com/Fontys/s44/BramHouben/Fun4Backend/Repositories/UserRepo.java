package com.Fontys.s44.BramHouben.Fun4Backend.Repositories;


import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends CrudRepository<User, UUID> {
    User getUserByUserId(UUID id);
    User getUserByName(String name);
    List<User>getAllByAge(int age);
    void deleteByName(String username);
}
