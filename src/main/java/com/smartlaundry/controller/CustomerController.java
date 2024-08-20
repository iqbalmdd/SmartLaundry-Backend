package com.smartlaundry.controller;

import com.smartlaundry.constant.APIUrl;
import com.smartlaundry.dto.Request.CustomerRequest;
import com.smartlaundry.dto.Request.CustomerUpdateRequest;
import com.smartlaundry.dto.Response.CommonResponse;
import com.smartlaundry.entity.Customer;
import com.smartlaundry.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> newCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customer = customerService.create(customerRequest);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Customer Created!")
                .data(customer)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<CommonResponse<List<Customer>>> getAll(@PathVariable String accountId){
        List<Customer> customer = customerService.getAllByAccount(accountId);
        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success Get All Customer")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> update(@RequestBody CustomerUpdateRequest customerUpdateRequest){
        Customer customer = customerService.update(customerUpdateRequest);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Customer Updated!")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.DELETE)
    public ResponseEntity<CommonResponse<Customer>> delete(@PathVariable String id){
        Customer customer = customerService.delete(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Customer Deleted!")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }
}
