package com.pavikumbhar.jpa.model;

public class OuterClass {
    private String value1;
    private String value2;
    private InnerClass innerClass;

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public  class InnerClass {
        public String value3;
        public String value4;

    }
}