package com.smartlaundry.repository;

import com.smartlaundry.entity.Account;
import com.smartlaundry.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    List<Customer> findByAccountAndActive(Account accountId, boolean active);
}
