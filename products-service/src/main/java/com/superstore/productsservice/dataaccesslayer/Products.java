package com.superstore.productsservice.dataaccesslayer;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private ProductsIdentifier productsIdentifier;


    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private Price price;

    @Embedded
    private Country country;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String productName;
    private String amount;

    public Products(ProductsIdentifier productsIdentifier, Price price, Country country, ProductType productType, String productName, String amount, Status status) {
        this.productsIdentifier = productsIdentifier;
        this.price = price;
        this.country = country;
        this.productType = productType;
        this.productName = productName;
        this.amount = amount;
        this.status = status;
    }

    public Products() {

    }
}
