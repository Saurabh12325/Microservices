package com.microservice.card.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String errorCode;
    private String errorMessage;
}
