package com.smartlaundry.service;

import com.smartlaundry.dto.Response.JwtClaims;
import com.smartlaundry.entity.Account;

public interface JwtService {
    String generateToken(Account account);
    boolean verifyJwtToken(String token);
    JwtClaims getClaimsByToken(String token);

}
