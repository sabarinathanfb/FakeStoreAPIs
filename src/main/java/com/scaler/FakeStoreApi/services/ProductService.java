package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);


    Product addNewProduct(ProductDTO product);


    Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, ProductDTO product);


    boolean deleteProduct(Long productId);

}

/** update product wit id 123
 * name : iPhone 15
 * desc:
 */