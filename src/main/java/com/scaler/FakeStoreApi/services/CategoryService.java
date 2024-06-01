package com.scaler.FakeStoreApi.services;

import org.springframework.web.bind.annotation.GetMapping;

public interface CategoryService {

    String getAllCategories();

    String getProductsInCategory();
}
