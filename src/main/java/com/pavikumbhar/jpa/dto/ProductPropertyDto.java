package com.pavikumbhar.jpa.dto;

import static com.pavikumbhar.jpa.constant.AppConstant.YYYY_MM_DD_HH_MM_SS;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pavikumbhar.jpa.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPropertyDto {

    private long productPropertyId;

    private String productPropertyCode;

    private long productId;

    private Status status;

    private String createdBy;

    private String modifiedBy;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime createdOn;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime modifiedOn;




}


