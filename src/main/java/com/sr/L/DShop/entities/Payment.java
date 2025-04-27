package com.sr.L.DShop.entities;

import com.sr.L.DShop.entities.abstractentity.AbstractBaseEntity;
import com.sr.L.DShop.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.aspectj.weaver.ast.Or;

@Entity
@SuperBuilder
@Getter
@Setter
public class Payment extends AbstractBaseEntity{
    //need external api then after only we can design this schema


    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


    @OneToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Orders orders;



}
