package com.microservice.accounts.dto;

import jakarta.persistence.Column;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "Account number can not be empty")
    @Pattern(regexp = "[0-9]{10}",message = "Account number should be 10 digits")
    private Long accountNumber;


    @NotEmpty(message = "Account type can not be empty")
    private String accountType;
   @NotEmpty(message = "Branch address can not be empty")
    private String branchAddress;
}
