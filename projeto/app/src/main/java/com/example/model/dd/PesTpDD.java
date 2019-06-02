package com.example.model.dd;

import com.example.model.beans.PessoaTipoBean;
import com.example.constants.Sit;

public class PesTpDD extends AbstracDD {
    public PesTpDD(){
        super();
    }


    public static Integer GERENTE  = 1;
    public static Integer MEDICO   = 1;
    public static Integer PACIENTE = 1;


    static {
        add(new PessoaTipoBean(GERENTE, Sit.ATIVO, "Gerente"));
        add(new PessoaTipoBean(MEDICO, Sit.ATIVO, "Médico"));
        add(new PessoaTipoBean(PACIENTE, Sit.ATIVO, "Paciente"));
    }

}
