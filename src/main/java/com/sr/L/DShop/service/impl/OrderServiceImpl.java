package com.sr.L.DShop.service.impl;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.entities.LdUser;
import com.sr.L.DShop.entities.OrderedItems;
import com.sr.L.DShop.entities.Orders;
import com.sr.L.DShop.entities.Products;
import com.sr.L.DShop.exceptions.ProductException;
import com.sr.L.DShop.exceptions.UnauthorizedException;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.OrderRequest;
import com.sr.L.DShop.repo.OrderRepo;
import com.sr.L.DShop.repo.OrderedItemsRepo;
import com.sr.L.DShop.repo.ProductRepo;
import com.sr.L.DShop.repo.UserRepo;
import com.sr.L.DShop.service.OrderService;
import com.sr.L.DShop.utils.TagHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final OrderedItemsRepo orderedItemsRepo;


    @Override
    public ResponseModel setOrder(OrderRequest orderRequest) {
        String orderTag = TagHelper.generateRandomTag(10);

        Orders userOrder = Orders
                .builder()
                .orderTag(orderTag)
                .ldUser(getAuthenticatedUserEntity())
                .build();

        int size = orderRequest.getOrderItems().size();


        for(int i = 0; i<size; i++){
            Products product =  getProducts(orderRequest.getOrderItems().get(i).getOrderId());
            String qty = orderRequest.getOrderItems().get(i).getProductQuantity();
            int totalPrice  = Integer.parseInt(product.getProductPrice())*Integer.parseInt(qty);

            OrderedItems items = OrderedItems
                    .builder()
                    .orderTag(orderTag)
                    .productName(product.getProductName())
                    .Quantity(qty)
                    .totalPrice(Integer.toString(totalPrice))
                    .orders(userOrder)
                    .build();

            orderedItemsRepo.save(items);
        }

        orderRepo.save(userOrder);

        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("order_tag",orderTag);
        orderDetails.put("order_id",userOrder.getId());
        return ResponseBuilder.success("Order placed successfully",orderDetails);
    }

    private Orders dtoToEntityConverterHelper(){

        return null;

    }

    private UserDetails getAuthenticatedUser(){
        return (UserDetails) SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal();
    }

    private Products getProducts(Long id){
        Optional<Products> product = productRepo.findById(id);
        if(product.isEmpty()){
            throw new ProductException("No such product found");
        }

        return product.get();
    }

    private Long getAuthenticatedUserId() {
        UserDetails userDetails = getAuthenticatedUser();
        return userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UnauthorizedException("User not found"))
                .getId();
    }

    private LdUser getAuthenticatedUserEntity() {
        UserDetails userDetails = getAuthenticatedUser();
        Optional<LdUser> ldUser = userRepo.findByUsername(userDetails.getUsername());
        if(ldUser.isEmpty()){
            throw new IllegalStateException("Authenticated user not found");
        }

        return ldUser.get();

    }



}
