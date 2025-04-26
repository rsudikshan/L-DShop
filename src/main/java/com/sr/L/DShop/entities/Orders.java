package com.sr.L.DShop.entities;

import com.sr.L.DShop.entities.abstractentity.AbstractBaseEntity;
import com.sr.L.DShop.enums.PaymentStatus;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


}
