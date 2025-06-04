package com.microservices.loan.loan.entity;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.microservices.loan.loan.Audit.AuditAwareImpl;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime CreatedDate;
    @CreatedBy
    @Column(updatable = false)
    private String CreatedBy;

    @LastModifiedDate
    @Column(updatable = false)
    private LocalDateTime UpdatedDate;

    @LastModifiedBy
    @Column(updatable = false)
    private String UpdatedBy;
}
