package com.project.customer;

import com.project.customer.dto.CustomerRequest;
import com.project.customer.dto.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/create")
    public Long createCustomer(@RequestBody CustomerRequest request) {
        return customerService.createCustomer(request);
    }

    @PutMapping("/update")
    public void updateCustomer(@RequestBody CustomerRequest request) {
        customerService.updateCustomer(request);
    }

    @GetMapping("/findAll")
    public List<CustomerResponse> findAllCustomer() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/find/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/exist/{id}")
    public Boolean existById(@PathVariable Long id) {
        return customerService.existById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }


}
