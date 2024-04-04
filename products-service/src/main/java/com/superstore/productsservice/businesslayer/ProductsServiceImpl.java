package com.superstore.productsservice.businesslayer;

import com.superstore.productsservice.dataaccesslayer.Products;
import com.superstore.productsservice.dataaccesslayer.ProductsRepository;
import com.superstore.productsservice.mapperlayer.ProductsRequestMapper;
import com.superstore.productsservice.mapperlayer.ProductsResponseMapper;
import com.superstore.productsservice.prentationlayer.ProductsRequestModel;
import com.superstore.productsservice.prentationlayer.ProductsResponseModel;
import com.superstore.productsservice.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductsServiceImpl implements ProductsService {
    private ProductsRepository productsRepository;
    private ProductsResponseMapper productsResponseMapper;
    private ProductsRequestMapper productsRequestMapper;

    public ProductsServiceImpl(ProductsRepository productsRepository, ProductsResponseMapper productsResponseMapper,  ProductsRequestMapper productsRequestMapper) {
        this.productsRepository = productsRepository;
        this.productsResponseMapper = productsResponseMapper;
        this.productsRequestMapper = productsRequestMapper;
    }


    @Override
    public List<ProductsResponseModel> getProducts() {
        List<Products> products = productsRepository.findAll();
        return productsResponseMapper.entityToResponseModelList(products);
    }

    @Override
    public ProductsResponseModel getProductByProductId(String productId) {
        Products products = productsRepository.findProductsByProductsIdentifier_ProductId(productId);

        if(products == null){
            throw new NotFoundException("Unknown productId: "+productId);
        }
        return productsResponseMapper.entityToResponseModel(products);
    }

    @Override
    public ProductsResponseModel updateProducts(String productId, ProductsRequestModel productsRequestModel) {
        Products existingproducts = productsRepository.findProductsByProductsIdentifier_ProductId(productId);
        if(existingproducts == null){
            throw new NotFoundException("Unknown productId: "+productId);
        }

        Products updatedproducts = productsRequestMapper.requestModelToEntity(productsRequestModel);
        updatedproducts.setId(existingproducts.getId());
        updatedproducts.setProductsIdentifier(existingproducts.getProductsIdentifier());


        Products response = productsRepository.save(updatedproducts);

        return productsResponseMapper.entityToResponseModel(response);
    }

    @Override
    public ProductsResponseModel addProduct(ProductsRequestModel productsRequestModel) {
        Products products = productsRequestMapper.requestModelToEntity(productsRequestModel);

        return productsResponseMapper.entityToResponseModel(productsRepository.save(products));
    }

    @Override
    public void deleteProduct(String productId) {
        Products existingproducts = productsRepository.findProductsByProductsIdentifier_ProductId(productId);

        if (existingproducts == null){
            throw new NotFoundException("Unknown productId: "+productId);
        }
        productsRepository.delete(existingproducts);

    }
}
