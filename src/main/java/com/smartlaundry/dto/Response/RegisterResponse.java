package com.smartlaundry.dto.Response;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String id;
    private String name;
    private String address;
    private String phoneNo;
    private String email;
    private String createdAt;
}
