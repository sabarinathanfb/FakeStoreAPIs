package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import com.scaler.FakeStoreApi.models.Category;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.repositories.CategoryRepository;
import com.scaler.FakeStoreApi.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SelfProductServiceTest {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SelfProductService productService;

    @Test
    void testGetSingleProductThrowsExceptionWhenNoSuchProduct(){

        when(productRepository.findProductById(any())).thenReturn(null);
//        when(productRepository.findProductById(any())).thenReturn(null).thenCallRealMethod();

        assertThrows(NotFoundException.class,
                () -> productService.getSingleProduct(1L));

//        productService.getSingleProduct(1L)
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void savingProductsAndCategory() {
//        Category category = new Category();
//        category.setCategoryName("phones");
//        Category savedCategory = categoryRepository.save(category);
//
//        Product product = new Product();
//        product.setPrice(100);
//        product.setImageUrl("hello");
//        product.setCategory(category);
//        productRepository.save(product);
//
        Category category = new Category();
        category.setCategoryName("orion");
        categoryRepository.save(category);

        Product product = new Product();
        product.setPrice(101);
        product.setDescription("orionGlass");
        product.setImageUrl("4.jpg");
        product.setCategory(category);
        productRepository.save(product);
    }

    @Test
    void getAllProducts(){
        productService.getAllProducts();
    }


}