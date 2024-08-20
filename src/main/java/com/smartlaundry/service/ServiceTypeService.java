package com.smartlaundry.service;

import com.smartlaundry.dto.Request.ServiceTypeRequest;
import com.smartlaundry.dto.Response.ServiceResponse;
import com.smartlaundry.entity.ServiceType;

import java.util.List;

public interface ServiceTypeService {
    ServiceType create(ServiceTypeRequest request);
    ServiceType update(ServiceTypeRequest request);
    void delete(String id);

    ServiceResponse getService(String AccountId);
    List<String> getType(String serviceName);
}
