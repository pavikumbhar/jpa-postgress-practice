package com.pavikumbhar.jpa.enums;

import lombok.Getter;

@Getter
public enum Status {
    Active("Active"),
    InActive("InActive");

    private  final String statusCode;
     Status(String status){
        this.statusCode=status;
    }}