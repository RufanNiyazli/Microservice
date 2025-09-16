package com.project.product;

import com.project.product.dto.ProductPurchaseResponse;
import com.project.product.dto.ProductRequest;
import com.project.product.dto.ProductResponse;
import com.project.product.entity.Category;
import com.project.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .description(request.description())
                .available_quantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .name(request.name())
                .price(request.price())
                .build();

    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailable_quantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()


        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity

        );
    }
}
