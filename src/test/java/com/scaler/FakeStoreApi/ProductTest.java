package com.scaler.FakeStoreApi;

import com.scaler.FakeStoreApi.models.Category;
import com.scaler.FakeStoreApi.models.Product;
import com.scaler.FakeStoreApi.repositories.CategoryRepository;
import com.scaler.FakeStoreApi.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@SpringBootTest
public class ProductTest {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;



    @Test
    @Transactional
    @Rollback(value = false)
    void savingProductsAndCategory() {
        Category category = new Category();
        category.setCategoryName("phones");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setPrice(100);
        product.setImageUrl("hello");
        product.setCategory(category);
        productRepository.save(product);
//
//        Category category = new Category();
//        category.setCategoryName("electronics");
//        Category savedCategory = categoryRepository.save(category);
//
//        Product product = new Product();
//        product.setPrice(101);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        productRepository.save(product);
    }



    @Test
    @Transactional
    void getProductsForCategory()  {

//        Category category = categoryRepository.findById(102L).get();

        List<Category> categories = categoryRepository.findAllByIdIn(List.of(152L,102L));


        for (Category category : categories) {
            for(Product product : category.getProducts()) {
                System.out.println(product.getPrice());
            }
        }
    }

}
