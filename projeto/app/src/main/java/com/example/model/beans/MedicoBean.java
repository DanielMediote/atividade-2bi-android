package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;

@Table(name = "medico")
public class MedicoBean extends Bean {

    @Column(name = "med_id", field = "id", pk = true)
    private Integer id;

    @Column(name = "med_crm", field = "crm")
    private String crm;

    @Column(name = "pes_id", field = "pessoa")
    private Integer pessoa;

    public MedicoBean() {
        super();
    }

    public MedicoBean(Integer id, String crm, Integer pessoa) {
        super();
        this.id = id;
        this.crm = crm;
        this.pessoa = pessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
    }
}
