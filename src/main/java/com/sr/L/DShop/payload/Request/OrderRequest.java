package com.sr.L.DShop.payload.Request;

import com.sr.L.DShop.models.OrderItemsModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private List<OrderItemsModel> orderItems;
}
