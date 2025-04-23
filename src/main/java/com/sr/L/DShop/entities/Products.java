package com.sr.L.DShop.entities;

import com.sr.L.DShop.entities.abstractentity.AbstractBaseEntity;
import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adminId",referencedColumnName = "id")
    private LdUser adminId;

    private String productName;
    private String productPrice;


    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Categories category;

    private String imageFileName;



}
