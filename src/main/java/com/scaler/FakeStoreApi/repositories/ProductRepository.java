package com.scaler.FakeStoreApi.repositories;

import com.scaler.FakeStoreApi.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);

    Page<Product> findAll(Pageable pageable);

    Product findProductById(Long id);

    Page<Product> findAllByTitleContaining(String query, Pageable pageable);

    @Query(value = Queries.LAAO_PRODUCTS_WITH_ID_QUERY, nativeQuery = true)
    List<Product>  laaoProductsWithId(Long id);


    @Query("select p from Product p where p.id = :id and  p.category.categoryName = :categoryName")
    List<Product>  getByIdAndTitle(Long id,String categoryName);

//    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
//    Long getLastInsertedProductId();



}
