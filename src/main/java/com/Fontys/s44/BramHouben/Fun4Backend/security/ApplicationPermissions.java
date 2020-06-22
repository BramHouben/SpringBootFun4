package com.Fontys.s44.BramHouben.Fun4Backend.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public enum  ApplicationPermissions {
    BASICUSER_READ("basicuser:read"),
    BASICUSER_WRITE("basicuser:write"),
    PRODUCT_WRITE("product:write"),
    PRODUCT_READ("product:read"),
    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write")
    ;



    private final String permission;

    ApplicationPermissions(String permission){
        this.permission=permission;
    }

    public String getPermission() {
        return permission;
    }



}
