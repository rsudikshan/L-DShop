package com.sr.L.DShop.entities;

import com.sr.L.DShop.entities.abstractentity.AbstractBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
public class OrderedItems extends AbstractBaseEntity {

    private String productName;
    private String Quantity;
    private String totalPrice;
    private String orderTag;


    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id")
    private Orders orders;

}
