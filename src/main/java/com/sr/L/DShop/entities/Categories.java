package com.sr.L.DShop.entities;

import com.sr.L.DShop.entities.abstractentity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.User;

@Entity
@Getter
@Setter
@SuperBuilder
public class Categories extends AbstractBaseEntity {

    @Column(unique = true)
    private String categoryName;


    @ManyToOne
    @JoinColumn(name = "superAdminId",referencedColumnName = "id")
    private User addedBy;


}
