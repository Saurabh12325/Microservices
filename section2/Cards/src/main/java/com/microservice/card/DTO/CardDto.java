package com.microservice.card.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private String MobileNumber;
    private String cardNumber;
    private String cardType;
    private int tatalLimit;
    private int amountUsed;
    private int availableAmount;
}
