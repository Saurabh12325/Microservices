package com.microservices.loan.loan.Service;

import com.microservices.loan.loan.dto.LoanDto;

public interface LoanService {

    void createLoan(String mobileNumber);
    LoanDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoanDto loansDto);
    boolean deleteLoan(String mobileNumber);
}
