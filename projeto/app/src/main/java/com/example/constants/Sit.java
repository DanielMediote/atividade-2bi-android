package com.example.constants;

public enum Sit {

    UNKNOWN(-1, "UNKNOWN"),

    ATIVO (1, "Ativo"),
    CANCELADO(2, "Cancelado"),
    INATIVO(3, "Inativo");


    private String descricao;
    private Integer id;

    Sit(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId(){
        return this.id;
    }


    public static Sit getItemById(Integer id){
        for (Sit sit : values()){
            if (sit.id.equals(id)) return  sit;
        }
        return UNKNOWN;
    }

}
