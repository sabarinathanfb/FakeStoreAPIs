package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
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
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Product product = productRepository.findProductById(productId);

        if (product == null) {
            throw new NotFoundException("Product Doesn't Exist");
        }

        return Optional.of(product);
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
