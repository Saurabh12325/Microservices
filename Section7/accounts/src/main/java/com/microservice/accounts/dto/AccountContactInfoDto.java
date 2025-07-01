package com.microservice.accounts.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
@Data
@ConfigurationProperties(prefix = "accounts")
public class AccountContactInfoDto {
   private String message;
   private Map<String,String> contactDetails;
    private List<String> onCallSupport;

}
