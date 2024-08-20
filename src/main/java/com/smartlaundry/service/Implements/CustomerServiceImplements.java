package com.smartlaundry.service.Implements;

import com.smartlaundry.dto.Request.CustomerRequest;
import com.smartlaundry.dto.Request.CustomerUpdateRequest;
import com.smartlaundry.entity.Account;
import com.smartlaundry.entity.Customer;
import com.smartlaundry.repository.CustomerRepository;
import com.smartlaundry.service.AccountService;
import com.smartlaundry.service.CustomerService;
import com.smartlaundry.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplements implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final Validation validation;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer create(CustomerRequest customerRequest) {
        validation.validate(customerRequest);

        Account account = accountService.getById(customerRequest.getAccountId());
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .phoneNo(customerRequest.getPhoneNo())
                .active(true)
                .account(account)
                .build();
        return customerRepository.saveAndFlush(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer update(CustomerUpdateRequest customerUpdateRequest) {
        validation.validate(customerUpdateRequest);
        Customer customer = customerRepository.findById(customerUpdateRequest.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer Not Found"));
        if (customerUpdateRequest.getAddress() != null && !customerUpdateRequest.getAddress().isEmpty()) {
            customer.setAddress(customerUpdateRequest.getAddress());
        }
        if (customerUpdateRequest.getName() != null && !customerUpdateRequest.getName().isEmpty()) {

            customer.setName(customerUpdateRequest.getName());
        }
        if (customerUpdateRequest.getPhoneNo() != null && !customerUpdateRequest.getPhoneNo().isEmpty()) {
            customer.setPhoneNo(customerUpdateRequest.getPhoneNo());
        }
        return customerRepository.saveAndFlush(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer delete(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer Not Foundd"));
        customer.setActive(false);
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getAllByAccount(String accountId) {
        Account account = accountService.getById(accountId);
        return customerRepository.findByAccountAndActive(account, true);
    }
}
