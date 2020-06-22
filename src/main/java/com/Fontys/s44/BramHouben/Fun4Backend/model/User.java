package com.Fontys.s44.BramHouben.Fun4Backend.model;

import com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.security.PublicKey;
import java.util.UUID;

@Entity
@Table(name = "useraccount")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "UUID", columnDefinition = "BINARY(16)")
    private UUID userId;

    private String name;

    private int age;

    private String gender;

    private String password;

    public UserRoles roles;

    public User() {

    }


    public User(@JsonProperty("id") UUID userId, @JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("gender") String gender, @JsonProperty("password") String password) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.password = password;
    }

    public User(UUID userId, String name,  int age,  String gender, String password, UserRoles roles) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.roles= roles;
    }


    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }

    public UserRoles getRoles() {
        return roles;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
