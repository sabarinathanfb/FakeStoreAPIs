package com.scaler.FakeStoreApi.controller;

import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import com.scaler.FakeStoreApi.models.Category;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.repositories.ProductRepository;
import com.scaler.FakeStoreApi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;


    public ProductController(ProductService productService,ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;

    }


    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long productId) throws NotFoundException {

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token","dawdjkajwdlncad");

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if (productOptional.isEmpty()) {
            throw new NotFoundException("No Product with product id: " + productId);
        }

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND
        );

        return response;


    }

    @PostMapping("")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO product) {

//        Product newProduct = productService.addNewProduct(
//                product
//        );

        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setTitle(product.getTitle());
        newProduct.setImageUrl(product.getImage());

        newProduct = productRepository.save(newProduct);

        return new ResponseEntity<>(
                newProduct,
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,
                                @RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setCategory(new Category());
        product.getCategory().setCategoryName(productDTO.getCategory());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public String DeleteProduct(@PathVariable Long productId) {
        return "Product deleted with id: " + productId;
    }

}
