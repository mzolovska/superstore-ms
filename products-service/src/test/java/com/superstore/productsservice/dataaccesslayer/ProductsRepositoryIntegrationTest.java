package com.superstore.productsservice.dataaccesslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductsRepositoryIntegrationTest {

    @Autowired
    private ProductsRepository productsRepository;

    @BeforeEach
    public void setDB(){
        productsRepository.deleteAll();
    }

    @Test
    public void whenProductsExists_ReturnProductsByProductsId(){
        Products products1 = new Products(new ProductsIdentifier("1234"), new Price("12"), new Country("Canasa"), ProductType.MEAT, "Meat","Fifty", Status.SOLD);


        productsRepository.save(products1);

        //act
        Products products = productsRepository.findProductsByProductsIdentifier_ProductId(products1.getProductsIdentifier().getProductId());

        //assert
        assertNotNull(products);
        assertEquals(products.getProductsIdentifier(), products1.getProductsIdentifier());
        assertEquals(products.getProductName(), products1.getProductName());
        assertEquals(products.getProductType(), products1.getProductType());
    }

    @Test
    public void whenProductsExists_ReturnNotFound_NegativePath() {
        Products products1 = new Products(new ProductsIdentifier("1234"), new Price("12"), new Country("Canasa"), ProductType.MEAT, "Meat", "Fifty", Status.SOLD);

    }
}