package com.project.customer;

import ch.qos.logback.core.util.StringUtil;
import com.project.customer.dto.CustomerRequest;
import com.project.customer.dto.CustomerResponse;
import com.project.customer.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public Long createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = this.customerRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Tapilmadi!"));
        mergeCustomer(request, customer);
        this.customerRepository.save(customer);

    }


    public List<CustomerResponse> findAllCustomer() {
        return this.customerRepository.findAll().stream().map(this.customerMapper::fromCustomer).
                collect(Collectors.toList());
    }

    public CustomerResponse findById(Long id) {
        return this.customerRepository.findById(id).map(customerMapper::fromCustomer).orElseThrow(() -> new RuntimeException("Tapilmadi!"));

    }

    public Boolean existById(Long id) {
        return this.customerRepository.findById(id).isPresent();
    }

    public void deleteCustomerById(Long id) {
        this.customerRepository.deleteById(id);

    }


    private void mergeCustomer(CustomerRequest request, Customer customer) {
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if (StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public boolean existsById(Long id) {
        return customerRepository.findById(id)
                .isPresent();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }
}
