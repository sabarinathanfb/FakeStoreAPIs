package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.Mapper.ProductMapper;
import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private RedisTemplate<Long, Object> redisTemplate;

    public SelfProductService(ProductRepository productRepository,RedisTemplate<Long, Object> redisTemplate) {
        this.redisTemplate =redisTemplate;
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

        String hashKey = "PRODUCTS"; // The hash key name

        // Try to get all products from Redis
        Map<Object, Object> productsMap = redisTemplate.opsForHash().entries(1L);

        List<Product> products;

        if (!productsMap.isEmpty()) {
            // Convert the map values to a list of products
            products = new ArrayList<>();
            for (Map.Entry<Object, Object> entry : productsMap.entrySet()) {
                products.add((Product) entry.getValue());
            }
        } else {
            // If products are not found in Redis, retrieve from the database
            products = productRepository.findAll();

            // Cache the products in Redis
            for (Product product : products) {
                redisTemplate.opsForHash().put( product.getId(),hashKey, product);
            }
        }

        return products;

//        return ;
    }


    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Product product = (Product)redisTemplate.opsForHash().get(productId,"PRODUCT");

        if(product != null){
            return Optional.of(product);
        }

        product = productRepository.findProductById(productId);

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
