package com.project.product;

import com.project.product.dto.ProductPurchaseRequest;
import com.project.product.dto.ProductPurchaseResponse;
import com.project.product.dto.ProductRequest;
import com.project.product.dto.ProductResponse;
import com.project.product.entity.Product;
import com.project.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Long createProduct(ProductRequest request) {
        Product product = mapper.toProduct(request);
        return repository.save(product).getId();

    }

    public ProductResponse findById(Long id) {
        return repository.findById(id).map(mapper::toProductResponse).orElseThrow(() -> new EntityNotFoundException("Product Not Found with Id: " + id));


    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests) throws ProductPurchaseException {
        var productIds = requests.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var sortedProducts = requests.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedProducts.get(i);
            if (product.getAvailable_quantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var new_available_quantity = product.getAvailable_quantity() - productRequest.quantity();
            product.setAvailable_quantity(new_available_quantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }
}
