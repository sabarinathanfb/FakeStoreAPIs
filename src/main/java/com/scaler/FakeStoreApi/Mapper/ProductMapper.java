package com.scaler.FakeStoreApi.Mapper;

import com.scaler.FakeStoreApi.dtos.ProductDTO;
import com.scaler.FakeStoreApi.models.Category;
import com.scaler.FakeStoreApi.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {


    public static Product toProduct(ProductDTO productDTO) {

        Category category = new Category();
        category.setCategoryName(productDTO.getCategory());

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        product.setCreatedAt(product.getCreatedAt());

        return product;
    }

    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setImage(product.getImageUrl());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory().getCategoryName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        if (products == null) {
            return null;
        }

        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Page<ProductDTO> toDTOPage(Page<Product> products) {
        if (products == null) {
            return null;
        }
        return products.map(ProductMapper::toDTO);
    }
}
