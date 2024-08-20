package com.smartlaundry.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceTypeRequest {
    @NotBlank(message = "Name is Empty")
    private String name;

    @NotBlank(message = "Type is Empty")
    private String type;

    @NotBlank(message = "Price is Empty")
    private long price;

    @NotBlank(message = "Account_id is Empty")
    private String accountId;

    private String imagePath;
}
