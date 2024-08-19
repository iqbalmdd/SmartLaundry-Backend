package com.smartlaundry.repository;

import com.smartlaundry.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceTypeRepository extends JpaRepository<ServiceType,String>, JpaSpecificationExecutor<ServiceType> {
}
