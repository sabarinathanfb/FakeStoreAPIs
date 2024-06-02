package com.scaler.FakeStoreApi.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Category extends BaseModel{


    private String categoryName;
    private String categoryDescription;

    private List<Product> products;
}
