package com.microservices.loan.loan.Controller;

import com.microservices.loan.loan.Constants.LoanConstants;
import com.microservices.loan.loan.Service.LoanService;
import com.microservices.loan.loan.dto.LoanContactInfoDto;
import com.microservices.loan.loan.dto.LoanDto;
import com.microservices.loan.loan.dto.ResponseDto;
import com.microservices.loan.loan.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private LoanContactInfoDto loanContactInfoDto;

    @PostMapping("/createLoan")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanConstants.STATUS_201,LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetchLoan")
    public ResponseEntity<LoanDto> fetchLoan(@RequestParam String mobileNumber){
      LoanDto loanDto =  loanService.fetchLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanDto);
    }

    @PutMapping("/updateLoan")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody LoanDto loanDto){
        boolean isUpdated = loanService.updateLoan(loanDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(LoanConstants.STATUS_417,LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/deleteLoan")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam String mobileNumber){
        boolean isDeleted = loanService.deleteLoan(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoanConstants.STATUS_417,LoanConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/version")
    public ResponseEntity<String> getBuildVersion(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }
    @GetMapping("/loanInfo")
    public ResponseEntity<LoanContactInfoDto> getLoanContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(loanContactInfoDto);
    }
}
