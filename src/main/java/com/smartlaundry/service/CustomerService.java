package com.smartlaundry.service;

import com.smartlaundry.dto.Request.CustomerRequest;
import com.smartlaundry.dto.Request.CustomerUpdateRequest;
import com.smartlaundry.entity.Customer;

public interface CustomerService {
    Customer create(CustomerRequest customerRequest);
    Customer update(CustomerUpdateRequest customerUpdateRequest);
    Customer delete(String id);
}
