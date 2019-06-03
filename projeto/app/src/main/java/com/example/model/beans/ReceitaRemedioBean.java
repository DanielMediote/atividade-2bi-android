package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;

@Table(name = "receitaremedio")
public class ReceitaRemedioBean extends Bean {

    @Column(name = "recrem_id", field = "id", pk = true)
    private Integer id;

    @Column(name = "rec_id", field = "receita")
    private Integer receita;

    @Column(name = "rem_id", field = "remedio")
    private Integer remedio;


    public ReceitaRemedioBean() {
        super();
    }

    public ReceitaRemedioBean(Integer id, Integer receita, Integer remedio) {
        super();
        this.id = id;
        this.receita = receita;
        this.remedio = remedio;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceita() {
        return receita;
    }

    public void setReceita(Integer receita) {
        this.receita = receita;
    }

    public Integer getRemedio() {
        return remedio;
    }

    public void setRemedio(Integer remedio) {
        this.remedio = remedio;
    }
}
