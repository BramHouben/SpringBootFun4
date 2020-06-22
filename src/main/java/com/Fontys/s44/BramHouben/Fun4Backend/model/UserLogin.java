package com.Fontys.s44.BramHouben.Fun4Backend.model;

import com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles;

public class UserLogin {


    private String name;

    public UserRoles roles;

    public UserLogin(String name, UserRoles roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }

    public UserRoles getRoles() {
        return roles;
    }
}
