package com.pavikumbhar.jpa.repository;

import com.pavikumbhar.jpa.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query("SELECT c FROM Customer c WHERE (:firstName is null or LOWER(c.firstName) LIKE LOWER(concat(:firstName, '%'))) AND " +
           " (:lastName is null or LOWER(c.lastName) LIKE LOWER(concat(:lastName, '%'))) AND" +
            "(:email is null or LOWER(c.email) LIKE LOWER(concat(:email, '%')))")
    Page<Customer> findCustomerByNameAndEmail(@Param("firstName") String firstName,
                                              @Param("lastName") String lastName,
                                              @Param("email") String email,
                                              Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) like lower(concat('%', :nameToFind,'%'))")
    public List<Customer> findByNameFree(@Param("nameToFind") String name);

}