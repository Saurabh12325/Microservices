package com.microservices.loan.loan.Mapper;

import com.microservices.loan.loan.dto.LoanDto;
import com.microservices.loan.loan.entity.Loan;

public class LoanMapper {

  public static LoanDto maptoDto(Loan loan , LoanDto loanDto){
    loanDto.setLoanNumber(loan.getLoanNumber());
    loanDto.setLoanType(loan.getLoanType());
    loanDto.setAmountPaid(loan.getAmountPaid());
    loanDto.setOutstandingAmount(loan.getOutstandingAmount());
    loanDto.setMobileNumber(loan.getMobileNumber());
    loanDto.setTotalLoan(loan.getTotalLoan());
    return loanDto;
  }

  public  static Loan mapToEntity(LoanDto loanDto , Loan loan){
    loan.setLoanNumber(loanDto.getLoanNumber());
    loan.setLoanType(loanDto.getLoanType());
    loan.setTotalLoan(loanDto.getTotalLoan());
    loan.setMobileNumber(loanDto.getMobileNumber());
    loan.setAmountPaid(loanDto.getAmountPaid());
    loan.setOutstandingAmount(loanDto.getOutstandingAmount());
    return loan;
  }


}
