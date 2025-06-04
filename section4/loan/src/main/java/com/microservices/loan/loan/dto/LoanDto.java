package com.microservices.loan.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private int amountPaid;
    private int outstandingAmount;
    private int totalLoan;

}
