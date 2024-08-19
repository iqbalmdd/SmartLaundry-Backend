package com.smartlaundry.security;

import com.smartlaundry.dto.Response.JwtClaims;
import com.smartlaundry.entity.Account;
import com.smartlaundry.service.AccountService;
import com.smartlaundry.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    final String AUTH_HEADER = "Authorization";
    private final JwtService jwtService;
    private final AccountService accountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = request.getHeader(AUTH_HEADER);

            // VERIFY TOKEN
            if (bearerToken != null && jwtService.verifyJwtToken(bearerToken)){
                // CLAIM TOKEN
                JwtClaims decodeJWT = jwtService.getClaimsByToken(bearerToken);
                // FIND ACCOOUNT BY ID in token subject
                Account accountbySub = accountService.getById(decodeJWT.getAccountId());
                // verify Authentication use UserPassAuthToken
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        accountbySub.getUsername(),
                        null,
                        accountbySub.getAuthorities()
                );

                // ADD OTHER DETAIL (IP ADDRESS, WHO'S Hitting
                authenticationToken.setDetails(new WebAuthenticationDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e){
            log.error("Cant Set User Authentication : {}", e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
