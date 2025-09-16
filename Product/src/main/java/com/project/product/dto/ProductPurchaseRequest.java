package com.project.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(@NotNull(message = "Product is mandatory")
                                     Long productId,
                                     @Positive(message = "Quantity is mandatory")
                                     double quantity) {
}
