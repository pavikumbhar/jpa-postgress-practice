package com.pavikumbhar.jpa.specification;

import com.pavikumbhar.jpa.model.Customer;

import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;

public class CustomerSpecifications {
    private CustomerSpecifications(){}

    public static Specification<Customer> firstNameContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("firstName"), contains(expression));
    }

    public static Specification<Customer> lastNameContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("lastName"), contains(expression));
    }

    public static Specification<Customer> emailContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("email"), contains(expression));
    }


    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }




}
