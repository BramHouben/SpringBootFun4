package com.Fontys.s44.BramHouben.Fun4Backend.jwt;

public class UsernamePasswordModel {

    private String username;
    private String password;


    public UsernamePasswordModel(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
