package com.smartlaundry.repository;

import com.smartlaundry.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,String>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByEmail (String email);
    boolean existsByEmail(String email);

}
