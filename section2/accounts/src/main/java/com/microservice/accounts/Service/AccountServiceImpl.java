package com.microservice.accounts.Service;

import com.microservice.accounts.Constants.AccountConstants;
import com.microservice.accounts.Entity.Account;
import com.microservice.accounts.Entity.Customer;
import com.microservice.accounts.Exception.CustomerAlreadyExistsExceptions;
import com.microservice.accounts.Exception.ResourceNotFoundException;
import com.microservice.accounts.Mapper.AccountMapper;
import com.microservice.accounts.Mapper.CustomerMapper;
import com.microservice.accounts.Repository.AccountRepository;
import com.microservice.accounts.Repository.CustomerRepository;
import com.microservice.accounts.dto.AccountDto;
import com.microservice.accounts.dto.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service

@AllArgsConstructor
public class AccountServiceImpl implements AccuntService {

   private final AccountRepository accountRepository;
   private  final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer =  customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsExceptions("Customer already exists with given mobileNumber " + customerDto.getMobileNumber());
        }
//          customer.setCreatedAt(LocalDateTime.now());
//          customer.setCreatedBy("Anonymous");
       Customer savedCustomer =  customerRepository.save(customer);
       accountRepository.save(createNewAccount(savedCustomer));
    }



    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextLong(900000000);
        newAccount.setAccountNumber((randomAccNumber));
        newAccount.setAccountType(AccountConstants.SAVING);
        newAccount.setBranchAddress(AccountConstants.BRANCH_ADDRESS);
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
   Customer customer =  customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
           () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));

    Account account =  accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
          () -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
  );
    CustomerDto customerDto = CustomerMapper.mapT0CustomerDto(customer,new CustomerDto());
    customerDto.setAccountDto(AccountMapper.mapToAccountDto(account,new AccountDto()));
    return customerDto;
}

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto != null){
            Account account = accountRepository.findById(accountDto.getAccountNumber())
                    .orElseThrow(()->new ResourceNotFoundException("Account","accountNumber",accountDto.getAccountNumber().toString()));
            AccountMapper.mapToAccount(accountDto,account);
            account =  accountRepository.save(account);
            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","customerId",customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto,customer);
             customerRepository.save(customer);
            isUpdate = true;
        }

        return isUpdate;


    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer", "mobileNumber", "mobileNumber"));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}
