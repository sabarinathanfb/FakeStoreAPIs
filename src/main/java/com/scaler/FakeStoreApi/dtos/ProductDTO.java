package com.scaler.FakeStoreApi.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class ProductDTO {


    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
    private RatingDTO rating;


}
