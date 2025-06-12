package com.microservice.card.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
@Data
@ConfigurationProperties(prefix = "card")
public class CardContactInfoDto  {
    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;
}
