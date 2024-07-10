package com.scaler.FakeStoreApi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel{


    private String categoryName;
    private String categoryDescription;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category",cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SELECT)
    private List<Product> products;
}
