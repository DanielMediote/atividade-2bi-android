package com.example.model.dd;

import com.example.constants.Sit;
import com.example.model.beans.PessoaTipoBean;
import com.example.model.beans.RemedioTipoBean;

import java.util.ArrayList;
import java.util.List;

public class RemTpDD {


    private static List<RemedioTipoBean> list = new ArrayList<>();


    public RemTpDD() {
        super();
    }

    public static Integer GOTAS = 1;
    public static Integer COMPRIMIDO = 2;


    static {
        add(new RemedioTipoBean(GOTAS, Sit.ATIVO, "Gotas"));
        add(new RemedioTipoBean(COMPRIMIDO, Sit.ATIVO, "Comprimidos"));
    }


    protected static void add(RemedioTipoBean obj) {
        list.add(obj);
    }
    public static List<RemedioTipoBean> getList() {
        return list;
    }

}
