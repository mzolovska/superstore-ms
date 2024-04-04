package com.superstore.productsservice.prentationlayer;

import com.superstore.productsservice.dataaccesslayer.Country;
import com.superstore.productsservice.dataaccesslayer.Price;
import com.superstore.productsservice.dataaccesslayer.ProductType;
import com.superstore.productsservice.dataaccesslayer.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductsResponseModel {
    String productId;
    String productName;
    String amount;
    Price price;
    Country country;
    Status status;
    ProductType productType;


}
