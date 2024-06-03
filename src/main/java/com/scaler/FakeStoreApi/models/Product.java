package com.scaler.FakeStoreApi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel {

    private String title;
    private String description;
    private double price;
    // P : C
    // 1 -> 1
    // M <- 1
    // M : 1
    @ManyToOne
    private Category category;
    private String imageUrl;
}
