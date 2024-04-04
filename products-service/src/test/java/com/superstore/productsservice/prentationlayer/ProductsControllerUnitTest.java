package com.superstore.productsservice.prentationlayer;

import com.superstore.productsservice.businesslayer.ProductsService;
import com.superstore.productsservice.dataaccesslayer.Country;
import com.superstore.productsservice.dataaccesslayer.ProductType;
import com.superstore.productsservice.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ProductsController.class)
class ProductsControllerUnitTest {

    private final String FOUND_PRODUCT_ID = "pqr312bc-pqr1-21pq-7893ikjnjhjh";

    private final String NOT_FOUND_PRODUCT_ID = "pqr312bc-pqr1-21pq-7893ikjnjh12";

    private final String INVALID_PRODUCT_ID = "pqr312bc-pqr1-21pq-7893i";

    @Autowired
    ProductsController productsController;

    @MockBean
    private ProductsService productsService;

    @Test
    public void GetProducts_thenReturnAllProducts(){
        //arrange
        when(productsService.getProducts()).thenReturn(Collections.emptyList());
        //act
        ResponseEntity<List<ProductsResponseModel>> responseEntity = productsController.getProducts();
        //assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isEmpty());
        verify(productsService, times(1)).getProducts();
    }




    private ProductsResponseModel buildProductsResponseModel() {
        return ProductsResponseModel.builder()
                .productId("pqr312bc-pqr1-21pq-7893ikjnjhjh")
                .productName("Beef")
                .amount("ten")
                .build();
    }

    private ProductsRequestModel buildProductsRequestModel() {

        return ProductsRequestModel.builder()

                .productId("pqr312bc-pqr1-21pq-7893ikjnjhjh")
                .productName("Beef")
                .amount("ten")
                .build();
    }

    @Test
    public void whenProductNotFoundOnGet_ThenThrowNotFoundException(){
        when(productsService.getProductByProductId(NOT_FOUND_PRODUCT_ID)).thenThrow(new NotFoundException("Unknown productId: "+NOT_FOUND_PRODUCT_ID));
        //act
        NotFoundException exception = assertThrowsExactly(NotFoundException.class, ()->
                productsController.getProductByProductId(NOT_FOUND_PRODUCT_ID));
        //assert

        assertEquals("Unknown productId: "+NOT_FOUND_PRODUCT_ID, exception.getMessage());
        verify(productsService, times(1)).getProductByProductId(NOT_FOUND_PRODUCT_ID);
    }



    @Test
    public void whenProductFoundOnUpdate_ThenReturnUpdatedProduct(){
        //arrange
        ProductsRequestModel productsRequestModel = buildProductsRequestModel();
        ProductsResponseModel productsResponseModel = buildProductsResponseModel();
        when(productsService.updateProducts(FOUND_PRODUCT_ID, productsRequestModel)).thenReturn(productsResponseModel);
        //act
        ResponseEntity<ProductsResponseModel> responseEntity = productsController.updateProduct(productsRequestModel, FOUND_PRODUCT_ID);
        //assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(productsResponseModel, responseEntity.getBody());
        verify(productsService, times(1)).updateProducts(FOUND_PRODUCT_ID, productsRequestModel);
    }

    @Test
    public void whenProductNotFoundOnUpdate_ThenThrowNotFoundException(){
        //arrange
        ProductsRequestModel productsRequestModel = buildProductsRequestModel();
        when(productsService.updateProducts(NOT_FOUND_PRODUCT_ID, productsRequestModel)).thenThrow(new NotFoundException("Unknown productId: "+NOT_FOUND_PRODUCT_ID));
        //act
        NotFoundException exception = assertThrowsExactly(NotFoundException.class, ()->
                productsController.updateProduct(productsRequestModel, NOT_FOUND_PRODUCT_ID));
        //assert
        assertEquals("Unknown productId: "+NOT_FOUND_PRODUCT_ID, exception.getMessage());
        verify(productsService, times(1)).updateProducts(NOT_FOUND_PRODUCT_ID, productsRequestModel);
    }


}