package com.superstore.productsservice.mapperlayer;

import com.superstore.productsservice.dataaccesslayer.Products;
import com.superstore.productsservice.prentationlayer.ProductsRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface ProductsRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productsIdentifier", ignore = true)
    @Mapping(target = "price", ignore = true)
    Products requestModelToEntity(ProductsRequestModel productsRequestModel);
}
