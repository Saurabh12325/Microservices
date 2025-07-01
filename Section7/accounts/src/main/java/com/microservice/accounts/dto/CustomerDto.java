package com.microservice.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Customer details with Account details"
)
public class CustomerDto {
@Schema(
    name = "Saurabh Srivastav",
    description = "Customer name"
)
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 5,max = 30,message = "The length of customer name should be between 5 and 30")
    private String name;
@Schema(

    description = "Customer email address"
)

    @NotEmpty(message = "Email address can not be empty")
    @Email(message = "Email address should be valid value")
    private String email;

   @Schema(
    description = "Customer mobile number",example = "1234567890"
   )
    @Pattern(regexp = "[0-9]{10}",message = "Mobile number should be 10 digits")
    private String mobileNumber;
@Schema(
        name = "Account",
        description = "Account detail of the customer"
)
    private AccountDto accountDto;
}
