package com.superstore.productsservice.mapperlayer;

import com.superstore.productsservice.dataaccesslayer.Products;
import com.superstore.productsservice.prentationlayer.ProductsResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ProductsResponseMapper {
    @Mapping(expression = "java(products.getProductsIdentifier().getProductId())", target = "productId")
    ProductsResponseModel entityToResponseModel(Products products);

    List<ProductsResponseModel> entityToResponseModelList(List<Products> products);

   /*@AfterMapping
   default void addLinks(@MappingTarget ProductsResponseModel model, Products products) {
       //self link
       model.add(linkTo(methodOn(ProductsController.class)
               .getProductByProductId(model.getProductId()))
               .withSelfRel());

       model.add(linkTo(methodOn(ProductsController.class).getProducts()).withRel("products"));
   }*/


}
