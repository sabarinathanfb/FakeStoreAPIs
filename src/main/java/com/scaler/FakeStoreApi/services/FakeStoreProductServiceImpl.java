package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.dtos.ProudctDTO;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    @Override
    public String getAllProducts() {
        return "";
    }

    @Override
    public String getProductById(Long Id) {
        return null;
    }

    @Override
    public String addNewProduct(ProudctDTO productsDTO) {
        return "";
    }

    @Override
    public String UpdateProduct(Long productId) {
        return "";
    }

    @Override
    public String DeleteProduct(Long productId) {
        return "";
    }
}
