package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;

@Table(name = "remedio")
public class RemedioBean extends Bean {

    @Column(name = "rem_id", field = "id", pk = true)
    private  Integer id;

    @Column(name = "rem_descricao", field = "descricao")
    private String descricao;

    @Column(name = "remt_id", field = "remedioTipo")
    private Integer remedioTipo;


    public RemedioBean() {
        super();
    }

    public RemedioBean(Integer id, String descricao, Integer remedioTipo) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.remedioTipo = remedioTipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getRemedioTipo() {
        return remedioTipo;
    }

    public void setRemedioTipo(Integer remedioTipo) {
        this.remedioTipo = remedioTipo;
    }
}
