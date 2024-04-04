package com.superstore.productsservice.prentationlayer;

import com.superstore.productsservice.businesslayer.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Slf4j
@RestController
@RequestMapping("api/v1/products")
public class ProductsController {

    private ProductsService productsService;

    public ProductsController(ProductsService productsService){this.productsService=productsService;}


    @GetMapping()
    public ResponseEntity<List<ProductsResponseModel>> getProducts(){
        return  ResponseEntity.status(HttpStatus.FOUND).body(productsService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductsResponseModel> getProductByProductId(@PathVariable String productId){
        return ResponseEntity.status(HttpStatus.FOUND).body(productsService.getProductByProductId(productId));
    }

    @PostMapping()
    public  ResponseEntity<ProductsResponseModel> addProduct(@RequestBody ProductsRequestModel productsRequestModel){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.addProduct(productsRequestModel));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductsResponseModel> updateProduct(@RequestBody ProductsRequestModel productsRequestModel, @PathVariable String productId){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.updateProducts(productId, productsRequestModel));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductsResponseModel> deleteProduct(@PathVariable String productId){
        productsService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}
