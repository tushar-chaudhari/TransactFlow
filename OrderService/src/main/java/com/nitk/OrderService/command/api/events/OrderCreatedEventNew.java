package com.nitk.OrderService.command.api.events;

import lombok.Data;

@Data
public class OrderCreatedEventNew {

    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;
}
