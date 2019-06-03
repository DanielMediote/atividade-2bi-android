package com.example.model.beans;

import com.example.annotations.Column;
import com.example.annotations.Table;

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


    public ReceitaBean() {
        super();
    }

    public ReceitaBean(Integer id, Integer medico, Integer paciente, String observacao) {
        super();
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.observacao = observacao;
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
}
