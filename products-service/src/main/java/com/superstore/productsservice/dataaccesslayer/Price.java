package com.superstore.productsservice.dataaccesslayer;

import jakarta.persistence.Embeddable;

@Embeddable
public class Price {
    private String price;


    public Price(String  price) {
        this.price = price;
    }

    public Price() {

    }

    public Price(Price price) {
    }
}
