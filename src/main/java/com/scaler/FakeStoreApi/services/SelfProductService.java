package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.Mapper.ProductMapper;
import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    public Page<Product> getProducts(int numberOfProducts,int offset){

        //productRepository.executeQuery("Select * from products limit  offset")

        Page<Product> products = productRepository.findAll(
                PageRequest.of((offset/numberOfProducts),numberOfProducts)
        );



        return products;
    }

    public Page<Product> getProductsByTitle(String title,int numberOfProducts,int offset){


        return productRepository.findAllByTitleContaining(title,PageRequest.of((offset/numberOfProducts),numberOfProducts));
    }




    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
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
    public Product addNewProduct(ProductDTO product){


        return productRepository.save(ProductMapper.toProduct(product));

    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        Product updatedProduct = productRepository.findProductById(productId);
        if (updatedProduct == null) {
            return null;
        }

        updatedProduct.setTitle(product.getTitle());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImageUrl(product.getImageUrl());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setLastUpdatedAt(Date.from(Instant.now()));

        return productRepository.save(updatedProduct);




    }


    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
