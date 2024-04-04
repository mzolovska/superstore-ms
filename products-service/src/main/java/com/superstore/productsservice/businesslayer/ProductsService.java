package com.superstore.productsservice.businesslayer;


import com.superstore.productsservice.prentationlayer.ProductsRequestModel;
import com.superstore.productsservice.prentationlayer.ProductsResponseModel;

import java.util.List;

public interface ProductsService {
    List<ProductsResponseModel> getProducts();
    ProductsResponseModel getProductByProductId(String productId);
    ProductsResponseModel updateProducts(String productId, ProductsRequestModel productsRequestModel);
    ProductsResponseModel addProduct(ProductsRequestModel productsRequestModel);
    void deleteProduct(String productId);
}

