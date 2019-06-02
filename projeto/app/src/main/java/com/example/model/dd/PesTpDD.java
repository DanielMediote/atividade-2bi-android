package com.example.model.dd;

import com.example.model.beans.Bean;
import com.example.model.beans.PessoaTipoBean;
import com.example.constants.Sit;

import java.util.ArrayList;
import java.util.List;

public class PesTpDD extends AbstracDD {

    private static List<PessoaTipoBean> list = new ArrayList<>();


    public PesTpDD() {
        super();
    }

    public static Integer GERENTE = 1;
    public static Integer MEDICO = 2;
    public static Integer PACIENTE = 3;


    static {
        add(new PessoaTipoBean(GERENTE, Sit.ATIVO, "Gerente"));
        add(new PessoaTipoBean(MEDICO, Sit.ATIVO, "Médico"));
        add(new PessoaTipoBean(PACIENTE, Sit.ATIVO, "Paciente"));
    }


    protected static void add(PessoaTipoBean obj) {
        list.add(obj);
    }
    public static List<PessoaTipoBean> getList() {
        return list;
    }
}