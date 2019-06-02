package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;
import com.example.constants.Sit;

@Table(name = "pessoatipo")
public class PessoaTipoBean extends Bean {

    @Column(name = "pestp_id", field = "id")
    private Integer id;

    @Column(name = "pestp_ativo", field = "ativo")
    private Integer ativo;

    @Column(name = "pestp_descricao", field = "descricao")
    private String descricao;


    public PessoaTipoBean() {
        super();
    }

    public PessoaTipoBean(Integer id, Sit ativo, String descricao) {
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

    public Sit getAtivo() {
        return Sit.getItemById(ativo);
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
