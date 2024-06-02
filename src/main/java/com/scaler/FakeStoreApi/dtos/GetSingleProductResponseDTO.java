package com.scaler.FakeStoreApi.dtos;

import com.scaler.FakeStoreApi.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDTO {

    private Product product;

}
