package com.scaler.FakeStoreApi.models;

import java.util.List;

public class Category extends BaseModel{


    private String categoryName;
    private String categoryDescription;

    private List<Product> products;
}
