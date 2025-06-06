package com.microservice.card.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Data
@EntityListeners(
        AuditingEntityListener.class)
@MappedSuperclass
@ToString
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
 private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
 private String createdBy;

    @Column(updatable = false)
    @LastModifiedDate
 private LocalDateTime updatedAt;

    @Column(updatable = false)
    @LastModifiedBy
 private String updatedBy;
}
