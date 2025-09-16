package com.project.product.dto;

import java.math.BigDecimal;

public record ProductPurchaseResponse(Long productId,
                                      String name,
                                      String description,
                                      BigDecimal price,
                                      double quantity) {
}
