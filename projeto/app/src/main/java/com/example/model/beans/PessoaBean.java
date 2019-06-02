package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Table(name = "pessoa")
public class PessoaBean extends Bean {

    @Column(name = "pes_id", field = "id")
    private Integer id;

    @Column(name = "pes_descricao", field = "descricao")
    private String descricao;

    @Column(name = "pes_login", field = "login")
    private String login;

    @Column(name = "pes_senha", field = "senha")
    private String senha;

    @Column(name = "pestp_id", field = "pessoaTipo")
    private Integer pessoaTipo;

    public PessoaBean() {
        super();
    }

    public PessoaBean(Integer id, String descricao, String login, String senha, Integer pessoaTipo) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.login = login;
        this.senha = senha;
        this.pessoaTipo = pessoaTipo;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPessoaTipo() {
        return pessoaTipo;
    }

    public void setPessoaTipo(Integer pessoaTipo) {
        this.pessoaTipo = pessoaTipo;
    }

}
