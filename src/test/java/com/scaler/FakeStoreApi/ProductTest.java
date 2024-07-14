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


@SpringBootTest
public class ProductTest {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;







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

    @Test
    public void demonstrateCustomQueries(){
        List<Product> products = productRepository.getByIdAndTitle(102L,"hi");
    }

}
