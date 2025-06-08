package com.microservice.accounts.Service;

import com.microservice.accounts.dto.CustomerDto;

public interface AccuntService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
