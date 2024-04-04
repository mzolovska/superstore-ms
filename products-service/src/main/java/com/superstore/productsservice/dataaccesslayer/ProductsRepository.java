package com.superstore.productsservice.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

Products findProductsByProductsIdentifier_ProductId(String productId);
}
