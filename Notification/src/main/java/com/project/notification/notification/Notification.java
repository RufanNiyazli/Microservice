package com.project.notification.notification;

import com.project.notification.kafka.order.OrderConfirmation;
import com.project.notification.kafka.payment.PaymentConfirmation;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Notification {
    @Id
    private Long id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
