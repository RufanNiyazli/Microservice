package com.project.order.kafka;

import com.project.order.customer.CustomerResponse;
import com.project.order.order.PaymentMethod;
import com.project.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
