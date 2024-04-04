package com.superstore.productsservice.prentationlayer;

import com.superstore.productsservice.dataaccesslayer.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("h2")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductsControllerIntegrationTest {

    private final String BASE_URI_PRODUCTS = "/api/v1/products";
    private final String FOUND_PRODUCT_ID = "bac312bc-bac1-21ba-31245abed45n";


    private final String NOT_FOUND_PRODUCT_ID = "pqr312bc-pqr1-21pq-7893ikjnjh12";

    private final String INVALID_PRODUCT_ID = "pqr312bc-pqr1-21pq-7893i";


    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void whenGetProducts_thenReturnAllProducts(){
        //arrange
        Long sizeDB = productsRepository.count();
        //act and assert
        //if u want to do post or put or delete instead of .get do .put or wtv
        webTestClient.get().uri(BASE_URI_PRODUCTS)
                .exchange()
                .expectStatus().isFound()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ProductsResponseModel.class)
                .value((list)->{
                    assertNotNull(list);
                    assertTrue(list.size() == sizeDB );
                });
    }

    @Test
    public void whenGetProductsDoesNotExist_thenReturnNotFound(){

        webTestClient.get().uri(BASE_URI_PRODUCTS + "/" + NOT_FOUND_PRODUCT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown productId: "+NOT_FOUND_PRODUCT_ID);

    }


    @Test
    public void whenGetProductById_thenReturnProduct(){
        //arrange
        Products products1 = new Products(new ProductsIdentifier("1234"), new Price("12"), new Country("Canasa"), ProductType.MEAT, "Meat","Fifty", Status.SOLD);
        productsRepository.save(products1);

        //act and assert
        webTestClient.get().uri(BASE_URI_PRODUCTS + "/" + products1.getProductsIdentifier().getProductId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isFound()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ProductsResponseModel.class)
                .value((product)->{
                    assertNotNull(product);
                    assertEquals(products1.getProductsIdentifier().getProductId(), product.getProductId());
                    assertEquals(products1.getProductName(), product.getProductName());
                    assertEquals(products1.getProductType(), product.getProductType());
                });
    }

    @Test
    public void whenGetProductByIdDoesNotExist_thenReturnNotFound(){
        //act and assert
        webTestClient.get().uri(BASE_URI_PRODUCTS + "/" + NOT_FOUND_PRODUCT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown productId: "+NOT_FOUND_PRODUCT_ID);
    }

    @Test
    public void whenPostProducts_thenReturnAllProducts(){
        //arrange
        Long sizeDB = productsRepository.count();
        ProductsRequestModel productsRequestModel = new ProductsRequestModel("123434232","Meat", "gigty", new Price("12"), new Country("Canada"));

        //act and assert
        //if u want to do post or put or delete instead of .get do .put or wtv
        webTestClient.post().uri(BASE_URI_PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(productsRequestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ProductsResponseModel.class)
                .value((product)->{
                    assertNotNull(product);
                    assertEquals(productsRequestModel.getProductName(), product.getProductName());
                    assertEquals(productsRequestModel.getCountry(), product.getCountry());
                });

        Long sizeDBAfter = productsRepository.count();
        assertEquals(sizeDB+1, sizeDBAfter);
    }

    @Test
    public void whenPostProductsInvalid_thenReturnBadRequest(){
        //arrange
        Long sizeDB = productsRepository.count();
        ProductsRequestModel productsRequestModel = new ProductsRequestModel("123434232","Meat", "gigty", new Price("12"), new Country("Canada"));

        //act and assert
        //if u want to do post or put or delete instead of .get do .put or wtv
        webTestClient.post().uri(BASE_URI_PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(productsRequestModel)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("BAD_REQUEST")
                .jsonPath("$.message").isEqualTo("Invalid productId: "+productsRequestModel.getProductId());

        Long sizeDBAfter = productsRepository.count();
        assertEquals(sizeDB, sizeDBAfter);
    }

    @Test
    public void whenPutProducts_thenReturnAllProducts(){
        //arrange
        Long sizeDB = productsRepository.count();
        ProductsRequestModel productsRequestModel = new ProductsRequestModel("123434232","Meat", "gigty", new Price("12"), new Country("Canada"));

        //act and assert
        //if u want to do post or put or delete instead of .get do .put or wtv
        webTestClient.put().uri(BASE_URI_PRODUCTS + "/" + FOUND_PRODUCT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(productsRequestModel)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ProductsResponseModel.class)
                .value((product)->{
                    assertNotNull(product);
                    assertEquals(productsRequestModel.getProductName(), product.getProductName());
                    assertEquals(productsRequestModel.getCountry(), product.getCountry());
                });

        Long sizeDBAfter = productsRepository.count();
        assertEquals(sizeDB, sizeDBAfter);
    }

    @Test
    public void whenPutProductsDoesNotExist_thenReturnNotFound(){
        //arrange
        ProductsRequestModel productsRequestModel = new ProductsRequestModel("123434232","Meat", "gigty", new Price("12"), new Country("Canada"));

        //act and assert
        //if u want to do post or put or delete instead of .get do .put or wtv
        webTestClient.put().uri(BASE_URI_PRODUCTS + "/" + NOT_FOUND_PRODUCT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(productsRequestModel)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown productId: "+NOT_FOUND_PRODUCT_ID);
    }

    @Test
    public void whenDeleteProducts_thenReturnAllProducts(){
        //arrange
        Long sizeDB = productsRepository.count();
        //act and assert
        //if u want to do post or put or delete instead of .get do .put or wtv
        webTestClient.delete().uri(BASE_URI_PRODUCTS + "/" + FOUND_PRODUCT_ID)
                .exchange()
                .expectStatus().isNoContent();

        Long sizeDBAfter = productsRepository.count();
        assertEquals(sizeDB-1, sizeDBAfter);
    }

    @Test
    public void whenDeleteProductsDoesNotExist_thenReturnNotFound(){
        //act and assert
        webTestClient.delete().uri(BASE_URI_PRODUCTS + "/" + NOT_FOUND_PRODUCT_ID)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo("NOT_FOUND")
                .jsonPath("$.message").isEqualTo("Unknown productId: "+NOT_FOUND_PRODUCT_ID);
    }









}