package com.project.notification.kafka.order;

public record Customer(
        Long id,
        String firstname,
        String lastname,
        String email
) {
}
