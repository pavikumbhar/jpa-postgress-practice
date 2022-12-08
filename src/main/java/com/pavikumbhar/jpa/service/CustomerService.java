package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.AppPage;
import com.pavikumbhar.jpa.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto addCustomer(CustomerDto customerDto);

    List<CustomerDto> searchCustomer(String firstName, String lastName, String email, int page, int size);

    List<CustomerDto> searchCustomerBySpecification(String firstName, String lastName, String email, int page,
            int size);

    List<CustomerDto> searchCustomerBySpecificationBuilder(String firstName, String lastName, String email, int page,
            int size);

    List<CustomerDto> searchCustomerByExample(String firstName, String lastName, String email, int page, int size);

    AppPage<CustomerDto> searchCustomer(String inputString, int page, int size);

    AppPage<CustomerDto> searchCustomerV2(String firstName, String lastName, String email, int page, int size);
}
