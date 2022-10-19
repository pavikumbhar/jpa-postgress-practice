package com.pavikumbhar.jpa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppResponse<T> {

    @Builder.Default
    private String status="Success";

    private T content;
}
