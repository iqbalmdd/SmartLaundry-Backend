package com.smartlaundry.repository;

import com.smartlaundry.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,String> {
}
