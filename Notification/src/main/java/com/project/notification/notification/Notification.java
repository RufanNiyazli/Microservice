package com.project.notification.notification;

import com.project.notification.kafka.order.OrderConfirmation;
import com.project.notification.kafka.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@Data
public class Notification {
    @Id
    private Long id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
