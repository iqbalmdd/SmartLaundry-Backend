package com.smartlaundry.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank(message = "Name is Empty")
    private String name;

    @NotBlank(message = "Address is Empty")
    private String address;

    @NotBlank(message = "PhoneNo is Empty")
    @Pattern(regexp = "^08\\d{9,12}$", message = "Mobile phone number's pattern must be started by 08 and has 9 until 13 digits")
    private String phoneNo;

    @NotBlank(message = "Owner Id is Empty")
    private String accountId;
}
