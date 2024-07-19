package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import com.scaler.FakeStoreApi.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Page<Product> getProducts(int numberOfProducts,int offset);
    Page<Product> getProductsByTitle(String title,int numberOfProducts,int offset);


    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;


    Product addNewProduct(ProductDTO product);


    Product updateProduct(Long productId, Product product);



    boolean deleteProduct(Long productId);

}

/** update product wit id 123
 * name : iPhone 15
 * desc:
 */