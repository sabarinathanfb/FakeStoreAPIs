package com.scaler.FakeStoreApi.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductRequestDto {

    private int numberOfResults;
    private int offset;
    private String title;
}
