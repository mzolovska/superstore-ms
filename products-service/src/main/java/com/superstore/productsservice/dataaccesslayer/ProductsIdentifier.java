package com.superstore.productsservice.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ProductsIdentifier {
    private String productId;

    public ProductsIdentifier(String productId) {
        this.productId = productId;
    }

    public ProductsIdentifier() {

    }
}
