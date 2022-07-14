package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.CustomerDto;
import com.pavikumbhar.jpa.mapper.CustomerMapper;
import com.pavikumbhar.jpa.model.Customer;
import com.pavikumbhar.jpa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements  CustomerService{

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    
    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerDto));
        return customerMapper.toCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> searchCustomer(String firstName, String lastName, String email,int page,int size ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepository.findCustomerByNameAndEmail(firstName, lastName, email, pageable);
        List<Customer> customerList=customerPage.getContent();
        return customerMapper.toCustomerDtoList(customerList);
    }

    @Override
    public List<CustomerDto> searchCustomerByExample(String firstName, String lastName, String email,int page,int size ) {
        Pageable pageable = PageRequest.of(page, size);


        ExampleMatcher matcher = ExampleMatcher
                .matching()
                 .withIgnoreCase("lastName", "firstName")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Customer customerExampleObject = Customer.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .build();
        Example<Customer> customerExample = Example.of(customerExampleObject,matcher);
        Page<Customer> customerPage  = customerRepository.findAll(customerExample,pageable);
        List<Customer> customerList=customerPage.getContent();
        return customerMapper.toCustomerDtoList(customerList);
    }
}
