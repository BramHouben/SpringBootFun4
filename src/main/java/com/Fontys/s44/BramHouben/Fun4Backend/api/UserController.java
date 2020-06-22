package com.Fontys.s44.BramHouben.Fun4Backend.api;

import com.Fontys.s44.BramHouben.Fun4Backend.authenticate.ApplicationUserDao;
import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import com.Fontys.s44.BramHouben.Fun4Backend.service.UserService;
import com.Fontys.s44.BramHouben.Fun4Backend.serviceInterfaces.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;


@RequestMapping("api/v1/user")
@RestController
public class UserController {


    private final IUserservice userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register")
    public ResponseEntity<?> addUser(@Valid @NotNull @RequestBody User user) {
        if (user.getName()==null||user.getPassword()==null){
            return ResponseEntity.badRequest().body("user is null");
        }   if (userService.insertUser(user)){
            return ResponseEntity.ok().body("user registered");
        }else {
            return ResponseEntity.badRequest().body("user already exist");
        }

    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

   // @PreAuthorize("hasAuthority('basicuser:read')")
    @GetMapping(path = "/getUsername")
    public String getUsernameCookie(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping(path = "/get/{id}")
    public User getUserById(@PathVariable("id") UUID uuid) {
        return userService.selectUserById(uuid).orElse(null);
    }

    @PreAuthorize("hasAuthority('basicuser:write')")
    @DeleteMapping()
    public void deleteUser(Authentication authentication) {
        String username = authentication.getName();
        userService.deleteUser(username);
    }

    @PreAuthorize("hasAuthority('basicuser:write')")
    @PutMapping()
    public ResponseEntity<?> updateUser(@Valid @NotNull @RequestBody User user, Authentication authentication) {
        if (authentication.getName() == null) {
            ResponseEntity.badRequest().body("No valid user!");
        }
        String username = authentication.getName();

        userService.updateUser(user, username);

        return ResponseEntity.ok().build();

    }


    @GetMapping(path = "/confirmCredentials")
    public boolean isUserAdmin(Authentication authentication) {
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLEADMIN"));

    }


}