package com.scaler.FakeStoreApi.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;
    private boolean isDeleted;


    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = new Date();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedAt = new Date();
    }

}
