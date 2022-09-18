package com.pavikumbhar.jpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ProductCriteria {
    @NotBlank
    private String query;
    @Min(0)
    private int offset = 0;
    @Min(1)
    private int limit = 10;

    @NotNull
    @Valid
    private SortCriteria sort;


}
