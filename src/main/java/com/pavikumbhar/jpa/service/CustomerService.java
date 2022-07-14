package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

     CustomerDto addCustomer(CustomerDto customerDto);

     List<CustomerDto> searchCustomer(String firstName,String lastName,String email,int page,int size);

     List<CustomerDto> searchCustomerByExample(String firstName,String lastName,String email,int page,int size);
}
