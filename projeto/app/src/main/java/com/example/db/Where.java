package com.example.db;

import com.example.constants.ConditionDB;

import java.util.HashMap;
import java.util.Map;

public class Where {

    private Map<String, Where > map = new HashMap<>();
    private ConditionDB condition;
    private Object value;


    public void add(String field, ConditionDB condition, Object value){
        Where where = new Where();
        where.setCondition(condition);

        if (condition.equals(ConditionDB.LIKE)){
            where.setValue("%"+value+"%");
        } else {
            where.setValue(value);
        }
        map.put(field, where);
    }

    private void setValue(Object value){
        this.value = value;
    }

    private void setCondition(ConditionDB condition){
        this.condition = condition;
    }


    public Object getValue() {
        return value;
    }

    public ConditionDB getCondition() {
        return condition;
    }

    public Map<String, Where> getItems(){
        return map;
    }

}
