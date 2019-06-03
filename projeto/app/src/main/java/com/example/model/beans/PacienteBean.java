package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;

@Table(name = "paciente")
public class PacienteBean extends Bean {

    @Column(name = "pac_id", field = "id", pk = true)
    private Integer id;

    @Column(name = "pac_cpf", field = "cpf")
    private String cpf;

    @Column(name = "pes_id", field = "pessoa")
    private Integer pessoa;


    public PacienteBean() {
        super();
    }

    public PacienteBean(Integer id, String cpf, Integer pessoa) {
        super();
        this.id = id;
        this.cpf = cpf;
        this.pessoa = pessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
    }
}
