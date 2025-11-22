package com.project.notification.kafka.payment;

import java.math.BigDecimal;
//bu class paymetn notficationrequestle eyni olmalidir ici
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
