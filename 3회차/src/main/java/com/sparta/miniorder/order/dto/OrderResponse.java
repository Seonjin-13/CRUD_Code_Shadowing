package com.sparta.miniorder.order.dto;

import com.sparta.miniorder.order.entity.Order;
import lombok.Getter;

@Getter
public class OrderResponse {
    private Long orderId;
    private String productName;
    private Integer price;

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.productName = order.getProduct().getName();
        this.price = order.getProduct().getPrice();
    }
}