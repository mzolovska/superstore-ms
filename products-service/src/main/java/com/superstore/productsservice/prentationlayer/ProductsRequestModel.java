package com.superstore.productsservice.prentationlayer;

import com.superstore.productsservice.dataaccesslayer.Country;
import com.superstore.productsservice.dataaccesslayer.Price;
import com.superstore.productsservice.dataaccesslayer.ProductType;
import com.superstore.productsservice.dataaccesslayer.Status;
import lombok.*;

@Value
@Builder

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductsRequestModel {

    public String productId;
    public String productName;
    public String amount;
    public Price price;
    public Country country;

}
