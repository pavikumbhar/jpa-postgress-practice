package com.pavikumbhar.jpa.service;

import com.pavikumbhar.jpa.dto.CustomerDto;
import com.pavikumbhar.jpa.mapper.CustomerMapper;
import com.pavikumbhar.jpa.model.Customer;
import com.pavikumbhar.jpa.repository.CustomerRepository;
import com.pavikumbhar.jpa.specification.CustomSpecificationBuilder;
import com.pavikumbhar.jpa.specification.CustomerSpecifications;
import com.pavikumbhar.jpa.specification.Filter;
import com.pavikumbhar.jpa.specification.QueryOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final CustomSpecificationBuilder customSpecificationBuilder;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerDto));
        return customerMapper.toCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> searchCustomer(String firstName, String lastName, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,Sort.by("customerId").descending());
        Page<Customer> customerPage = customerRepository.findCustomerByNameAndEmail(firstName, lastName, email,
                pageable);
        List<Customer> customerList = customerPage.getContent();
        return customerMapper.toCustomerDtoList(customerList);
    }

    @Override
    public List<CustomerDto> searchCustomerBySpecification(String firstName, String lastName, String email, int page,
            int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Customer> specification = Specification
                .where(isEmpty(firstName) ? null : CustomerSpecifications.firstNameContains(firstName))
                .and(isEmpty(lastName) ? null : CustomerSpecifications.lastNameContains(lastName))
                .and(isEmpty(email) ? null : CustomerSpecifications.emailContains(email));
        Page<Customer> customerPage = customerRepository.findAll(specification, pageable);
        List<Customer> customerList = customerPage.getContent();
        return customerMapper.toCustomerDtoList(customerList);
    }

    @Override
    public List<CustomerDto> searchCustomerBySpecificationBuilder(String firstName, String lastName, String email,
            int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Filter> filters = new ArrayList<>();
        if (!isEmpty(firstName)) {
            Filter firstNameLike = Filter.builder()
                    .field(Customer.Fields.firstName.name())
                    .operator(QueryOperator.LIKE)
                    .value(firstName)
                    .build();
            filters.add(firstNameLike);
        }

        if (!isEmpty(lastName)) {
            Filter lastNameLike = Filter.builder()
                    .field(Customer.Fields.lastName.name())
                    .operator(QueryOperator.LIKE)
                    .value(lastName)
                    .build();
            filters.add(lastNameLike);
        }

        if (!isEmpty(email)) {
            Filter emailLike = Filter.builder()
                    .field(Customer.Fields.email.name())
                    .operator(QueryOperator.LIKE)
                    .value(email)
                    .build();
            filters.add(emailLike);
        }

        Specification<Customer> andSpecificationFromFilters = customSpecificationBuilder
                .getAndSpecificationFromFilters(filters, Customer.class);
        Page<Customer> customerPage = customerRepository.findAll(andSpecificationFromFilters, pageable);
        List<Customer> customerList = customerPage.getContent();
        return customerMapper.toCustomerDtoList(customerList);
    }

    @Override
    public List<CustomerDto> searchCustomerByExample(String firstName, String lastName, String email, int page,
            int size) {
        Pageable pageable = PageRequest.of(page, size);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase(Customer.Fields.firstName.name(), Customer.Fields.lastName.name())
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Customer customerExampleObject = Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
        Example<Customer> customerExample = Example.of(customerExampleObject, matcher);
        Page<Customer> customerPage = customerRepository.findAll(customerExample, pageable);
        List<Customer> customerList = customerPage.getContent();
        return customerMapper.toCustomerDtoList(customerList);
    }
}
