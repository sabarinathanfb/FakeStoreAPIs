package com.scaler.FakeStoreApi.controller;

import com.scaler.FakeStoreApi.Mapper.ProductMapper;
import com.scaler.FakeStoreApi.clients.authenticationclient.AuthenticationClient;
import com.scaler.FakeStoreApi.clients.authenticationclient.dtos.Role;
import com.scaler.FakeStoreApi.clients.authenticationclient.dtos.SessionStatus;
import com.scaler.FakeStoreApi.clients.authenticationclient.dtos.ValidateTokenRequestDto;
import com.scaler.FakeStoreApi.clients.authenticationclient.dtos.ValidateTokenResponseDto;
import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import com.scaler.FakeStoreApi.models.Category;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.repositories.ProductRepository;
import com.scaler.FakeStoreApi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final AuthenticationClient authenticationClient;


    public ProductController(ProductService productService,ProductRepository productRepository,AuthenticationClient authenticationClient) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.authenticationClient = authenticationClient;

    }


    /* Make only admins be Able to Access all Products */
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                           @Nullable @RequestHeader("USER_ID") Long userId) {

//        if(token == null || userId == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        ValidateTokenResponseDto response = authenticationClient.validate(token, userId);
//
//        if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        // validate token
//        // RestTemplate rt = new RestTRemplate();
//        //  rt.get("localhost:9090/auth/validate?)
//
//        // check if user has permissions
//        boolean isUserAdmin = false;
//        for (Role role: response.getUserDto().getRoles()) {
//            if (role.getName().equals("ADMIN")) {
//                isUserAdmin = true;
//            }
//        }
//
//        if (!isUserAdmin) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }


        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = ProductMapper.toDTOList(products);

        return new ResponseEntity<>(productDTOs,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getSingleProduct(@PathVariable Long productId) throws NotFoundException {

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token","dawdjkajwdlncad");

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if (productOptional.isEmpty()) {
            throw new NotFoundException("No Product with product id: " + productId);
        }

        ProductDTO productDTO = ProductMapper.toDTO(productOptional.get());

        return new ResponseEntity<>(productDTO,headers,HttpStatus.OK);


    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO product) {

        Product newProduct = productService.addNewProduct(product);

        return new ResponseEntity<>(ProductMapper.toDTO(newProduct),HttpStatus.CREATED);

    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") Long productId,
                                @RequestBody ProductDTO productDTO) {

        Product product = ProductMapper.toProduct(productDTO);

        return new ResponseEntity<>(ProductMapper.toDTO(productService.updateProduct(productId,product)),
                HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{productId}")
    public String DeleteProduct(@PathVariable Long productId) {
        return "Product deleted with id: " + productId;
    }

}
