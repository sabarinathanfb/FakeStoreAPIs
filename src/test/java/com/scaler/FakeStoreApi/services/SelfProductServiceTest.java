package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import com.scaler.FakeStoreApi.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SelfProductServiceTest {


    @MockBean
    private ProductRepository productRepository;

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

}