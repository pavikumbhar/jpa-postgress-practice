package com.pavikumbhar.jpa.controller;

import com.pavikumbhar.jpa.dto.CustomerDto;

import com.pavikumbhar.jpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto customerDtoResponse = customerService.addCustomer(customerDto);
        return ResponseEntity.ok(customerDtoResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> searchCustomer(@RequestParam(required = false) String firstName,
                                                            @RequestParam(required = false) String lastName,
                                                            @RequestParam(required = false) String email,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size){
        List<CustomerDto> customerDtoList = customerService.searchCustomer(firstName,lastName, email,page,size);
        return ResponseEntity.ok(customerDtoList);
    }
}
