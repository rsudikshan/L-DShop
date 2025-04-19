package com.sr.L.DShop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Roles {
    ADMIN(Set.of(
            Permissions.ADMIN_CREATE,
            Permissions.ADMIN_READ,
            Permissions.ADMIN_DELETE,
            Permissions.ADMIN_UPDATE
    )),

    SUPER_ADMIN(
      Set.of(
              Permissions.SUPER_ADMIN_CREATE,
              Permissions.SUPER_ADMIN_READ,
              Permissions.SUPER_ADMIN_UPDATE,
              Permissions.SUPER_ADMIN_DELETE
      )
    ),


    USER(Collections.emptySet());

    private final Set<Permissions> authorities;

    public List<SimpleGrantedAuthority> loadAuthorities(){
        var auth = this.getAuthorities()
                .stream()
                .map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        auth.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return auth;
    }
}
