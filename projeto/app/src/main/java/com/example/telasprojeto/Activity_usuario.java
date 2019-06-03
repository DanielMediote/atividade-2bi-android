package com.example.telasprojeto;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.adapters.ReceitaAdapter;
import com.example.constants.ConditionDB;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.MedicoBean;
import com.example.model.beans.PacienteBean;
import com.example.model.beans.ReceitaBean;

import java.util.List;

public class Activity_usuario extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        listView = super.findViewById(R.id.listaView_receita);

        loadData();
    }


    private void loadData(){
        DBHelper dbHelp = new DBHelper(this);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Integer pes_id = sp.getInt("idUserLogado", -1);

        Where where = new Where();
        where.add("pessoa", ConditionDB.EQUALS, pes_id);

        List<PacienteBean> pacList = (List<PacienteBean>) dbHelp.select(PacienteBean.class, where);
        PacienteBean pacBean;
        if (pacList.size() > 0){
            pacBean = pacList.get(0);
        } else {
            pacBean = new PacienteBean();
        }

        where = new Where();
        where.add("paciente", ConditionDB.EQUALS, pacBean.getId());
        List<ReceitaBean> recList = (List<ReceitaBean>) dbHelp.select(ReceitaBean.class, where);

        ReceitaAdapter adapter = new ReceitaAdapter(recList, this);

        listView.setAdapter(adapter);
    }

}
