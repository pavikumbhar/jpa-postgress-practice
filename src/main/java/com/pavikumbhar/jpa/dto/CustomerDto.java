package com.pavikumbhar.jpa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

import static com.pavikumbhar.jpa.constant.AppConstant.YYYY_MM_DD_HH_MM_SS;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  CustomerDto {


    private long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String createdBy;

    private String modifiedBy;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime createdOn;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime modifiedOn;
}
