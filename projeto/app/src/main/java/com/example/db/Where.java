package com.example.db;

import java.util.HashMap;
import java.util.Map;

public class Where {

    Map<String, String> map = new HashMap<>();

    public void add(String field, Condition condition, Object value){
        StringBuilder sb = new StringBuilder(condition.value);
        sb.append(String.valueOf(value).toLowerCase());

        if (condition.equals(Condition.LIKE)){
            map.put(field, " %"+sb.toString()+"%");
        } else {
            map.put(field, " "+sb.toString());
        }
    }

    public Map<String, String> getItems(){
        return map;
    }

}



enum Condition{
    EQUALS("="),
    LIKE("LIKE");

    public String value;

    Condition(String value){
        this.value = value;
    }
}