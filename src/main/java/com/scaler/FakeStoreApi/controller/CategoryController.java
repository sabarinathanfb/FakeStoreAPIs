package com.scaler.FakeStoreApi.controller;

import com.scaler.FakeStoreApi.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String getAllCategories() {
        return "All categories";
    }

    public String getProductsInCategory() {
        return "Get products in category";
    }

}
