package com.project.product;

import com.project.product.dto.ProductPurchaseRequest;
import com.project.product.dto.ProductPurchaseResponse;
import com.project.product.dto.ProductRequest;
import com.project.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping("/create-product")
    public Long createProduct(@RequestBody @Valid ProductRequest request) {
        return service.createProduct(request);

    }

    @GetMapping("/find/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/findAll")
    public List<ProductResponse> findAll() {
        return service.findAll();
    }

    @PostMapping("/purchase")
    public List<ProductPurchaseResponse> purchaseProduct(@Valid @RequestBody List<ProductPurchaseRequest> requests) throws ProductPurchaseException {
        return service.purchaseProduct(requests);
    }

}
