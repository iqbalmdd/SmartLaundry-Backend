package com.smartlaundry.service.Implements;

import com.smartlaundry.constant.Type;
import com.smartlaundry.dto.Request.ServiceTypeRequest;
import com.smartlaundry.dto.Response.ServiceResponse;
import com.smartlaundry.entity.Account;
import com.smartlaundry.entity.ServiceType;
import com.smartlaundry.repository.ServiceTypeRepository;
import com.smartlaundry.service.AccountService;
import com.smartlaundry.service.ServiceTypeService;
import com.smartlaundry.utils.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTypeImplements implements ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;
    private final AccountService accountService;
    private final Validation validation;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServiceType create(ServiceTypeRequest request) {
        validation.validate(request);
        Account account = accountService.getById(request.getAccountId());
        ServiceType serviceType = ServiceType.builder()
                .name(request.getName())
                .imagePath(request.getImagePath())
                .type(Type.valueOf(request.getType()))
                .price(request.getPrice())
                .account(account)
                .build();
        return serviceTypeRepository.saveAndFlush(serviceType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServiceType update(ServiceTypeRequest request) {
        validation.validate(request);
        Account account = accountService.getById(request.getAccountId());
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ServiceResponse getService(String AccountId) {
        return null;
    }

    @Override
    public List<String> getType(String serviceName) {
        return List.of();
    }
}
