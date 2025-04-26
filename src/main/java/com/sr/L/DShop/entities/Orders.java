package com.sr.L.DShop.entities;

import com.sr.L.DShop.entities.abstractentity.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
public class Orders extends AbstractBaseEntity {

    @Column(unique = true)
    private String orderTag;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private LdUser ldUser;

}
