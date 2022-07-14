package com.pavikumbhar.jpa.enums;

import lombok.Getter;

@Getter
public enum Status {
    Active("Active"),
    InActive("InActive");

    private  final String status;
     Status(String status){
        this.status=status;
    }}