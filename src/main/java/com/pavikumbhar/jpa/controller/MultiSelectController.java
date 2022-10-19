package com.pavikumbhar.jpa.controller;

import com.pavikumbhar.jpa.dto.AppPage;
import com.pavikumbhar.jpa.dto.CustomerDto;

import com.pavikumbhar.jpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("multi-search")
@RequiredArgsConstructor
public class MultiSelectController {

    private final CustomerService customerService;

    @GetMapping("/search")
    public ResponseEntity <AppPage<CustomerDto>> searchCustomer(@RequestParam(required = false) String inputString,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size){
        logger.info("searchProduct inputString : {}",inputString);
        AppPage<CustomerDto> customers = customerService.searchCustomer(inputString, page, size);
        return  ResponseEntity.ok(customers);
    }
}
