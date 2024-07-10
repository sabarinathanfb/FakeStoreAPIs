package com.scaler.FakeStoreApi.repositories;

import com.scaler.FakeStoreApi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);
    Product findProductById(Long id);

}
