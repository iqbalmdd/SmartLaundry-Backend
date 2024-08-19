package com.smartlaundry.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Name is Empty")
    private String name;

    @NotBlank(message = "Address is Empty")
    private String address;

    @NotBlank(message = "PhoneNo is Empty")
    @Pattern(regexp = "^08\\d{9,11}$", message = "Mobile phone number's pattern must be started by 08 and has 9 until 12 digits")
    private String phoneNo;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Unknown format email")
    @NotBlank(message = "Email is Empty")
    private String email;

    @NotBlank(message = "Address is Empty")
    private String password;

    private MultipartFile image;
}
