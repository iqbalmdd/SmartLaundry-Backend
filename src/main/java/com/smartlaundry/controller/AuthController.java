package com.smartlaundry.controller;

import com.smartlaundry.constant.APIUrl;
import com.smartlaundry.dto.Request.LoginRequest;
import com.smartlaundry.dto.Request.RegisterRequest;
import com.smartlaundry.dto.Response.CommonResponse;
import com.smartlaundry.dto.Response.LoginResponse;
import com.smartlaundry.dto.Response.RegisterResponse;
import com.smartlaundry.entity.Account;
import com.smartlaundry.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.AUTH_API)
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = APIUrl.REGISTER)
    public ResponseEntity<CommonResponse<RegisterResponse>> registerAccount(@RequestBody RegisterRequest registerRequest){
        RegisterResponse register = authService.register(registerRequest);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Account Created!")
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = APIUrl.LOGIN)
    public ResponseEntity<CommonResponse<LoginResponse>> loginAccount(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authService.login(loginRequest);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Login Success")
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
