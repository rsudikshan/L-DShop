package com.sr.L.DShop.entities;

import com.sr.L.DShop.entities.abstractentity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.java.Log;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Products extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "adminId",referencedColumnName = "id")
    private LdUser userId;

    private String productName;
    private String productPrice;


    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @Column(unique = true)
    private Categories category;

}
