package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.dtos.ProudctDTO;
import org.springframework.web.bind.annotation.*;

public interface ProductService {

    String getAllProducts();

    String getProductById(Long Id);


    String addNewProduct(ProudctDTO productsDTO);


    String UpdateProduct(Long productId);


    String DeleteProduct(Long productId);
}
