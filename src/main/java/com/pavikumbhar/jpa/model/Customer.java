package com.pavikumbhar.jpa.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="CUSTOMER")
@FieldNameConstants(asEnum = true)
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private long customerId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @CreatedBy
    @Column(name="CREATED_BY")
    private String createdBy;

    @LastModifiedBy
    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @CreatedDate
    @Column(name="CREATED_ON")
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name="MODIFIED_ON")
    private LocalDateTime modifiedOn;
}
