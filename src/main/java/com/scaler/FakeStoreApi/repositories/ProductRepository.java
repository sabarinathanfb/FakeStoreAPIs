package com.scaler.FakeStoreApi.repositories;

import com.scaler.FakeStoreApi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);

    Product findProductById(Long id);

    @Query(value = Queries.LAAO_PRODUCTS_WITH_ID_QUERY, nativeQuery = true)
    List<Product>  laaoProductsWithId(Long id);


//    List<Product> products;
//    for(Product p: product)
//        if(p.id ={} and p.title ={})


    @Query("select p from Product p where p.id = :id and  p.category.categoryName = :categoryName")
    List<Product>  getByIdAndTitle(Long id,String categoryName);

}
