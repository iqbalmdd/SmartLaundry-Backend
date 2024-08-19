package com.smartlaundry.service;

import com.smartlaundry.dto.Request.LoginRequest;
import com.smartlaundry.dto.Request.RegisterRequest;
import com.smartlaundry.dto.Response.LoginResponse;
import com.smartlaundry.dto.Response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
