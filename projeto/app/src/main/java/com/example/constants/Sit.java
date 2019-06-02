package com.example.constants;

public enum Sit {
    ATIVO (1, "Ativo"),
    CANCELADO(2, "Cancelado"),
    INATIVO(2, "Inativo");


    private String descricao;
    private Integer id;

    Sit(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

}
