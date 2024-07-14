package com.scaler.FakeStoreApi.clients.authenticationclient.dtos;

import com.scaler.FakeStoreApi.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseModel {
    private String name;
}
