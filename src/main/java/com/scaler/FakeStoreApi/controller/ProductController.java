package com.scaler.FakeStoreApi.controller;

import com.scaler.FakeStoreApi.dtos.ProudctDTO;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;

    }

    @GetMapping("")
    public String getAllProducts() {
        return "All products";
    }

    @GetMapping("/{productId}")
    public String getProductById(@PathVariable("productId") Long Id) {
        return "Product with id: " + Id + " found";
    }

    @PostMapping("")
    public String addNewProduct(@RequestBody ProudctDTO productsDTO) {
        return "Product added" + productsDTO;
    }
    @PutMapping("/{productId}")
    public String UpdateProduct(@PathVariable  Long productId, ProudctDTO productsDTO) {
        return "Product updated";
    }

    @DeleteMapping("/{productId}")
    public String DeleteProduct(@PathVariable Long productId) {
        return "Product deleted with id: " + productId;
    }

}
