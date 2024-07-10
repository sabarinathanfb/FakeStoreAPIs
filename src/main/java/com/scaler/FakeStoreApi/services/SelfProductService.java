package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.repositories.ProductRepository;

import java.util.List;

public class SelfProductService implements ProductService {

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public Product addNewProduct(ProductDTO product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, ProductDTO product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
