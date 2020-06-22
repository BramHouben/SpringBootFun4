package com.Fontys.s44.BramHouben.Fun4Backend.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.Fontys.s44.BramHouben.Fun4Backend.security.ApplicationPermissions.*;

public enum UserRoles {
    ADMIN(Sets.newHashSet(PRODUCT_WRITE, PRODUCT_READ, ORDER_READ,ORDER_WRITE,USER_READ,USER_WRITE)),
    BASICUSER(Sets.newHashSet(BASICUSER_READ, BASICUSER_WRITE));

    private final Set<ApplicationPermissions> permissions;

    UserRoles(Set<ApplicationPermissions> permissions){
        this.permissions=permissions;
    }

    public Set<ApplicationPermissions> getPermissions() {
        return permissions;
    }



    public Set<SimpleGrantedAuthority> getGrantedAuthoritySet(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE"+this.name()));
        return permissions;
    }
}
