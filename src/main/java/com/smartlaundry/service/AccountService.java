package com.smartlaundry.service;

import com.smartlaundry.entity.Account;
import com.smartlaundry.entity.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    Account getById(String accoundId);
    Account getbyEmail(String email);
    Account update(Account account);
    Customer getCustomerByEmail(String email);
    Customer getServiceTypeByEmail(String ServiceType);
}
