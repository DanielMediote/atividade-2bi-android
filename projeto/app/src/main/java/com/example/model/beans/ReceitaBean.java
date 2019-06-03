package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;

import java.util.Date;

@Table(name = "receita")
public class ReceitaBean extends Bean {

    @Column(name = "rec_id", field = "id", pk = true)
    private Integer id;

    @Column(name = "med_id", field = "medico")
    private Integer medico;

    @Column(name = "pac_id", field = "paciente")
    private Integer paciente;

    @Column(name = "rec_observacao", field = "observacao")
    private String observacao;

    @Column(name = "rec_data", field = "data")
    private Date data;


    public ReceitaBean() {
        super();
    }

    public ReceitaBean(Integer id, Integer medico, Integer paciente, String observacao, Date data) {
        super();
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.observacao = observacao;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedico() {
        return medico;
    }

    public void setMedico(Integer medico) {
        this.medico = medico;
    }

    public Integer getPaciente() {
        return paciente;
    }

    public void setPaciente(Integer paciente) {
        this.paciente = paciente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
