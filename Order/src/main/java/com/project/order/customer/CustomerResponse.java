package com.project.order.customer;

public record CustomerResponse(Long  id,
                               String firstname,
                               String lastname,
                               String email) {
}
