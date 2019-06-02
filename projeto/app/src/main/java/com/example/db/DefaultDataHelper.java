package com.example.db;

import android.content.Context;

import com.example.constants.ConditionDB;
import com.example.model.beans.Bean;
import com.example.model.beans.PessoaBean;
import com.example.model.beans.PessoaTipoBean;
import com.example.model.beans.RemedioTipoBean;
import com.example.model.dd.PesTpDD;
import com.example.model.dd.RemTpDD;

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

//        dbHelp.deleteAll(PessoaTipoBean.class);
        addPesTipo();
        addRemTipo();

        if (temGerente() == false){
            addGerente();
        }

    }

    private boolean temGerente(){
        Where where = new Where();
        where.add("pessoaTipo", ConditionDB.EQUALS, PesTpDD.GERENTE);

        List<PessoaBean> list = (List<PessoaBean>) dbHelp.select(PessoaBean.class, where);

        if (list.size() > 0){
            return true;
        }

        return false;
    }


    private void addPesTipo(){

        List<PessoaTipoBean> list = (List<PessoaTipoBean>) dbHelp.select(PessoaTipoBean.class);

        if (list.size() > 0){
            dbHelp.deleteAll(PessoaTipoBean.class);
        }

        for (PessoaTipoBean pestpBean : PesTpDD.getList()) {
            dbHelp.insert(pestpBean);
        }
    }

    private void addRemTipo(){
        List<RemedioTipoBean> list = (List<RemedioTipoBean>) dbHelp.select(RemedioTipoBean.class);

        if (list.size() > 0){
            dbHelp.deleteAll(RemedioTipoBean.class);
        }
        for (RemedioTipoBean remtpBean : RemTpDD.getList()) {
            dbHelp.insert(remtpBean);
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
