package com.example.db;

import android.content.Context;
import android.widget.Toast;

import com.example.model.beans.Bean;
import com.example.model.beans.PessoaBean;
import com.example.model.dd.PesTpDD;

import java.util.List;

public class DefaultDataHelper {

    private Context context;
    private DBHelper dbHelp;


    public DefaultDataHelper(Context context){
        this.context = context;

        start();
    }


    private void start(){
        dbHelp = new DBHelper(context);

        temGerente();
    }

    private boolean temGerente(){
        Where where = new Where();
        where.add("pessoaTipo.id", Condition.EQUALS, PesTpDD.GERENTE);

        List<Bean> list = dbHelp.select(PessoaBean.class, where);

        if (list.size() > 0){
            Toast.makeText(context, "TEM GERENTE CADASTRADO", Toast.LENGTH_LONG).show();
            return true;
        }
        Toast.makeText(context, "NÃO TEM GERENTE CADASTRADO", Toast.LENGTH_LONG).show();

        return false;

    }
}
