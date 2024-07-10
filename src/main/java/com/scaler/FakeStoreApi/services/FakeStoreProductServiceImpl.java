package com.scaler.FakeStoreApi.services;

import com.scaler.FakeStoreApi.dtos.FakeStoreProductDTO;
import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.models.Category;
import com.scaler.FakeStoreApi.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }



    private Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        Category category = new Category();
        category.setCategoryName(productDTO.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDTO.getImage());

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class
        );

        List<Product> answer = new ArrayList<>();

        for (FakeStoreProductDTO productDTO: l.getBody()) {

            answer.add(convertFakeStoreProductDTOToProduct(productDTO));
        }
        return answer;
    }

    /**
     * Return a Project Object with all the details of the fetched product.
     * the id of the category will be null but the name of the category shall be correct.
     */


    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{1}",
                FakeStoreProductDTO.class, productId
        );

        FakeStoreProductDTO productDTO = response.getBody();



        return convertFakeStoreProductDTOToProduct(productDTO);
    }

    @Override
    public Product addNewProduct(ProductDTO product) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDTO.class
        );
        FakeStoreProductDTO productDTO = response.getBody();

        return convertFakeStoreProductDTOToProduct(productDTO);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setCategory(product.getCategory().getCategoryName());


        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDTO,
                FakeStoreProductDTO.class,
                productId
        );
//        FakeStoreProductDTO fakeStoreProductDtoResponse = restTemplate.patchForObject(
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDTO,
//                FakeStoreProductDTO.class,
//                productId
//        );


        return convertFakeStoreProductDTOToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long productId, ProductDTO product) {

        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
