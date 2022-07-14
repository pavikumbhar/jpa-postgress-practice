package com.pavikumbhar.jpa.mapper;

import com.pavikumbhar.jpa.dto.CustomerDto;

import com.pavikumbhar.jpa.model.Customer;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CustomerMapper {

    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .createdBy(customer.getCreatedBy())
                .createdOn(customer.getCreatedOn())
                .modifiedBy(customer.getModifiedBy())
                .modifiedOn(customer.getModifiedOn())
                .build();
    }

    public Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .customerId(customerDto.getCustomerId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .build();
    }

    public List<Customer> toCustomerList(List<CustomerDto>customerDtoList ){
      return  customerDtoList!=null?customerDtoList.stream()
                .map(this::toCustomer)
                .toList(): Collections.emptyList();
    }

    public List<CustomerDto> toCustomerDtoList(List<Customer>customerList ){
        return  customerList!=null?customerList.stream()
                .map(this::toCustomerDto )
                .toList(): Collections.emptyList();
    }



}
