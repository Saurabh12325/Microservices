package com.microservices.loan.loan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Setter
@ToString
public class Loan extends BaseEntity{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private int amountPaid;
    private int totalLoan;
    private int outstandingAmount;

}
