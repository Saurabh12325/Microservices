package com.microservices.loan.loan.Service;

import com.microservices.loan.loan.Constants.LoanConstants;
import com.microservices.loan.loan.Exception.LoanAlreadyExistsException;
import com.microservices.loan.loan.Exception.ResourceNotFoundException;
import com.microservices.loan.loan.Mapper.LoanMapper;
import com.microservices.loan.loan.Repository.LoanRepository;
import com.microservices.loan.loan.dto.LoanDto;
import com.microservices.loan.loan.entity.Loan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;


import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> loan = loanRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent()) {
        throw new LoanAlreadyExistsException("Loan already exists with given mobileNumber " + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan =  new Loan();
        newLoan.setMobileNumber(mobileNumber);
        long randomLoanNumber = 10000000000L + new Random().nextLong(9000000000L);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;

    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(()->
                new ResourceNotFoundException("Loan","mobileNumber" , mobileNumber));
        return LoanMapper.maptoDto(loan, new LoanDto());

    }

    @Override
    public boolean updateLoan(LoanDto loansDto) {
        boolean isUpdate;
        Loan loan = loanRepository.findByMobileNumber(loansDto.getMobileNumber())
                .orElseThrow(()-> new ResourceNotFoundException("Loan", "mobileNumber" , loansDto.getMobileNumber()));
        LoanMapper.mapToEntity(loansDto,loan);
        loanRepository.save(loan);
        isUpdate = true;
        return isUpdate;

    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        boolean isDelete;
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->
                new ResourceNotFoundException("Loan","mobileNumber", mobileNumber));
        loanRepository.delete(loan);
        isDelete = true;
        return isDelete;

    }
}
