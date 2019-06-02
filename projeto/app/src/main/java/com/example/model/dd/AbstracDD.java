package com.example.model.dd;

import com.example.model.beans.Bean;

import java.util.ArrayList;
import java.util.List;

public class AbstracDD {

    private static List<Bean> list = new ArrayList<>();

    public AbstracDD(){
        list = new ArrayList<>();
    }

    protected static void add(Bean obj){
        list.add(obj);
    }

}
