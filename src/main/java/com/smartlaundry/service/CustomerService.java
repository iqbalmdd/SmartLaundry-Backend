package com.smartlaundry.service;

import com.smartlaundry.dto.Request.CustomerRequest;
import com.smartlaundry.dto.Request.CustomerUpdateRequest;
import com.smartlaundry.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(CustomerRequest customerRequest);
    Customer update(CustomerUpdateRequest customerUpdateRequest);
    Customer delete(String id);
    List<Customer> getAllByAccount(String accountId);
}
