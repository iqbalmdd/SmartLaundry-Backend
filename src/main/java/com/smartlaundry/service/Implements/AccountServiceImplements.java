package com.smartlaundry.service.Implements;

import com.smartlaundry.entity.Account;
import com.smartlaundry.entity.Customer;
import com.smartlaundry.repository.AccountRepository;
import com.smartlaundry.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImplements implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account create(Account accountRequest) {
        return null;
    }

    @Override
    public Account getById(String accoundId) {
        return null;
    }

    @Override
    public Account getbyEmail(String email) {
        return null;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return null;
    }

    @Override
    public Customer getServiceTypeByEmail(String ServiceType) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email Not Found"));
    }

}
