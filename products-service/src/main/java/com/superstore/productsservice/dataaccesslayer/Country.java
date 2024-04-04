package com.superstore.productsservice.dataaccesslayer;

import jakarta.persistence.Embeddable;

@Embeddable
public class Country {
    private String country;

    public Country(String country) {
        this.country = country;
    }

    public Country() {

    }
}
