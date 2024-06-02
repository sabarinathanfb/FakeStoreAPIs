package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.models.Category;
import com.scaler.FakeStoreApi.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDTO[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductDTO[].class
        );

        List<Product> answer = new ArrayList<>();

        for (ProductDTO productDto: l.getBody()) {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Category category = new Category();
            category.setCategoryName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            answer.add(product);
        }
        return answer;
    }

    /**
     * Return a Project Object with all the details of the fetched product.
     * the id of the category will be null but the name of the category shall be correct.
     */


    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{1}",
                ProductDTO.class, productId
        );

        ProductDTO productDTO = response.getBody();

        Product product = new Product();

        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        Category category = new Category();
        category.setCategoryName(productDTO.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDTO.getImage());


        return product;
    }

    @Override
    public Product addNewProduct(ProductDTO product) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDTO> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                ProductDTO.class
        );
        ProductDTO productDTO = response.getBody();

        Product product1 = new Product();
        product1.setId(productDTO.getId());
        product1.setTitle(productDTO.getTitle());
        product1.setPrice(productDTO.getPrice());
        Category category = new Category();
        category.setCategoryName(productDTO.getCategory());
        product1.setCategory(category);
        product1.setImageUrl(productDTO.getImage());

        return product1;
    }

    @Override
    public Product UpdateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean DeleteProduct(Long productId) {
        return false;
    }
}
