package com.project.customer.dto;

import com.project.customer.entity.Address;
import lombok.Data;


public record CustomerResponse(
        Long id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
