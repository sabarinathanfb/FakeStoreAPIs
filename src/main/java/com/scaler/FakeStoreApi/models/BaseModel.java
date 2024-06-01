package com.scaler.FakeStoreApi.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class BaseModel {
    private Long productId;
    private Date createdAt;
    private Date lastUpdatedAt;
    private boolean isDeleted;
}
