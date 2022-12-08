package com.pavikumbhar.jpa.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@ToString
 class SortCriteria {

    @NotNull
    private SortOrder order;

    @NotBlank
    private String attribute;
}
