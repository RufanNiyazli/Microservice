package com.project.order.order;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        if (request == null) {
            return null;

        }
        return Order.builder().id(request.id()).reference(request.reference()).customerId(request.customerId()).paymentMethod(request.paymentMethod())

                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
