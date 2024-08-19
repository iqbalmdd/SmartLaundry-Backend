package com.smartlaundry.service.Implements;

import com.smartlaundry.dto.Request.LoginRequest;
import com.smartlaundry.dto.Request.RegisterRequest;
import com.smartlaundry.dto.Response.LoginResponse;
import com.smartlaundry.dto.Response.RegisterResponse;
import com.smartlaundry.entity.Account;
import com.smartlaundry.repository.AccountRepository;
import com.smartlaundry.service.AuthService;
import com.smartlaundry.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImplements implements AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PreAuthorize("permitAll()")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        String hashPassword = passwordEncoder.encode(registerRequest.getPassword());
        Account account = Account.builder()
                .name(registerRequest.getName())
                .address(registerRequest.getAddress())
                .phoneNo(registerRequest.getPhoneNo())
                .email(registerRequest.getEmail())
                .password(hashPassword)
                .createdAt(new Date())
                .build();
        Account newAccount = accountRepository.saveAndFlush(account);
        return RegisterResponse.builder()
                .id(newAccount.getId())
                .name(newAccount.getName())
                .address(newAccount.getAddress())
                .phoneNo(newAccount.getPhoneNo())
                .email(newAccount.getEmail())
                .createdAt(String.valueOf(newAccount.getCreatedAt()))
                .build();

    }

    @Transactional(readOnly = true)
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        Authentication authenticated = authenticationManager.authenticate(authentication);
        Account logedAccount = (Account) authenticated.getPrincipal();
        String token = jwtService.generateToken(logedAccount);
        return LoginResponse.builder()
                .name(logedAccount.getName())
                .token(token)
                .build();
    }


}
