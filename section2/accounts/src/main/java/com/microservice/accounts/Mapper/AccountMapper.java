package com.microservice.accounts.Mapper;

import com.microservice.accounts.Entity.Account;
import com.microservice.accounts.dto.AccountDto;

public class AccountMapper {

public static AccountDto mapToAccountDto(Account account , AccountDto accountDto) {
    accountDto.setAccountNumber(account.getAccountNumber());
    accountDto.setAccountType(account.getAccountType());
    accountDto.setBranchAddress(account.getBranchAddress());
    return accountDto;
}

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Maps an AccountDto to an Account, populating the accountNumber, accountType and
     * branchAddress fields of the Account with the corresponding fields of the AccountDto.
     *
     * @param account the Account to be mapped
     * @param accountDto the AccountDto containing the data to be mapped
     * @return the mapped Account
     */
/* <<<<<<<<<<  833a4e46-84cf-44e2-bb63-7e6848e61e77  >>>>>>>>>>> */
public static Account mapToAccount(AccountDto accountDto, Account account) {
    account.setAccountNumber(accountDto.getAccountNumber());
    account.setAccountType(accountDto.getAccountType());
    account.setBranchAddress(accountDto.getBranchAddress());
    return account;
}

}
