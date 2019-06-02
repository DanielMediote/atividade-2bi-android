package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;
import com.example.constants.Sit;

@Table(name = "remediotipo")
public class RemedioTipoBean extends Bean{

    @Column(name = "remt_id", field = "id", pk = true)
    private Integer id;

    @Column(name = "remt_ativo", field = "id")
    private Integer ativo;

    @Column(name = "remt_descricao", field = "id")
    private String descricao;

    public RemedioTipoBean() {
        super();
    }

    public RemedioTipoBean(Integer id, Sit ativo, String descricao) {
        super();
        this.id = id;
        this.ativo = ativo.getId();
        this.descricao = descricao;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Sit ativo) {
        this.ativo = ativo.getId();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
