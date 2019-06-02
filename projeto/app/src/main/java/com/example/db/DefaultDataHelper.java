package com.example.db;

import android.content.Context;
import android.widget.Toast;

import com.example.model.beans.Bean;
import com.example.model.beans.PessoaBean;
import com.example.model.beans.PessoaTipoBean;
import com.example.model.dd.PesTpDD;

import java.util.List;

public class DefaultDataHelper {

    private Context context;
    DBHelper dbHelp;


    public DefaultDataHelper(Context context){
        this.context = context;

        start();
    }


    private void start(){
        dbHelp = new DBHelper(context);

        dbHelp.deleteAll(PessoaTipoBean.class);
        addPesTipo();
        if (temGerente() == false){
            addGerente();
        }

    }

    private boolean temGerente(){
        Where where = new Where();
        where.add("pessoaTipo", Condition.EQUALS, PesTpDD.GERENTE);

        List<Bean> list = dbHelp.select(PessoaBean.class, where);

        if (list.size() > 0){
//            Toast.makeText(context, "TEM GERENTE CADASTRADO", Toast.LENGTH_LONG).show();
            return true;
        }
//        Toast.makeText(context, "NÃO TEM GERENTE CADASTRADO", Toast.LENGTH_LONG).show();

        return false;
    }


    private void addPesTipo(){

        List<Bean> list =  dbHelp.select(PessoaTipoBean.class);

        if (list.size() > 0){
            dbHelp.deleteAll(PessoaTipoBean.class);
        }

        for (PessoaTipoBean pestpBean : PesTpDD.getList()) {
            dbHelp.insert(pestpBean);
        }
    }


    private void addGerente(){
        PessoaBean pesBean = new PessoaBean();
        pesBean.setDescricao("TESTE");
        pesBean.setLogin("04047654248");
        pesBean.setPessoaTipo(PesTpDD.GERENTE);
        pesBean.setSenha("123");

        dbHelp.insert(pesBean);
    }
}
