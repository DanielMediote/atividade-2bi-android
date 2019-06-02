package com.example.constants;

public enum ConditionDB {
    EQUALS("="),
    LIKE("LIKE"),
    IN("IN");

    public String value;

    ConditionDB(String value){
        this.value = value;
    }

}
