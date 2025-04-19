package com.sr.L.DShop.entities;

import com.sr.L.DShop.entities.abstractentity.AbstractBaseEntity;
import com.sr.L.DShop.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LdUser extends AbstractBaseEntity implements UserDetails {

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;
    private String password;
   // private String contactNo;


    @Enumerated(EnumType.STRING)
    private Roles role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.loadAuthorities();
    }
}
