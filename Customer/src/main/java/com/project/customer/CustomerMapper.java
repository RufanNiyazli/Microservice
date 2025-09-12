package com.project.customer;

import com.project.customer.dto.CustomerRequest;
import com.project.customer.dto.CustomerResponse;
import com.project.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null;

        }
        return Customer.builder()
                .id(request.id())
                .address(request.address())
                .email(request.email())
                .lastname(request.lastname())
                .address(request.address())

                .build();

    }

    public CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
