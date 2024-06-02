package com.scaler.FakeStoreApi.controller;

import com.scaler.FakeStoreApi.dtos.GetSingleProductResponseDTO;
import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;

    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long productId) {

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token","dawdjkajwdlncad");


        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND
        );


        return  response;
    }

    @PostMapping("")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO product) {

        Product newProduct = productService.addNewProduct(
                product
        );
        ResponseEntity<Product> response = new ResponseEntity<>(
                newProduct,
                HttpStatus.CREATED
        );

        return response;
    }

    @PutMapping("/{productId}")
    public String UpdateProduct(@PathVariable  Long productId, ProductDTO productsDTO) {
        return "Product updated";
    }

    @DeleteMapping("/{productId}")
    public String DeleteProduct(@PathVariable Long productId) {
        return "Product deleted with id: " + productId;
    }

}
