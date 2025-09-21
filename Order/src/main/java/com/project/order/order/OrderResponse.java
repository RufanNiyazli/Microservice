package com.project.order.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(Long id,
                            String reference,
                            BigDecimal amount,
                            PaymentMethod paymentMethod,
                            Long customerId) {
}
