package com.example.telasprojeto;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapters.ReceitaAdapter;
import com.example.adapters.RemedioAdapter;
import com.example.constants.ConditionDB;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.MedicoBean;
import com.example.model.beans.PacienteBean;
import com.example.model.beans.PessoaBean;
import com.example.model.beans.ReceitaBean;
import com.example.model.beans.ReceitaRemedioBean;
import com.example.model.beans.RemedioBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class Activity_usuario extends AppCompatActivity {

    ListView listView;
    DBHelper dbHelp;
    ReceitaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        dbHelp = new DBHelper(this);
        listView = super.findViewById(R.id.listaView_receita);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_usuario.this);

                ReceitaBean recBean = (ReceitaBean) adapter.getItem(position);

                MedicoBean medBean = (MedicoBean) dbHelp.selectById(MedicoBean.class, recBean.getMedico());
                PessoaBean pesMedBean = (PessoaBean) dbHelp.selectById(PessoaBean.class, medBean.getPessoa());

                PacienteBean pacBean = (PacienteBean) dbHelp.selectById(PacienteBean.class, recBean.getPaciente());
                PessoaBean pesPacBean = (PessoaBean) dbHelp.selectById(PessoaBean.class, pacBean.getPessoa());

                StringBuilder sb = new StringBuilder();
                sb.append("Médico: ");
                sb.append(pesMedBean.getDescricao());
                sb.append("\n");
                sb.append("Paciente: ");
                sb.append(pesPacBean.getDescricao());
                sb.append("\n");
                sb.append("Data: ");
                sb.append(new SimpleDateFormat("dd/MM/yyyy").format(recBean.getData()));
                sb.append("\n");

                builder.setMessage(sb.toString());

                LayoutInflater inflater = Activity_usuario.super.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_detail_receita, null);

                ListView listView = dialogView.findViewById(R.id.list_view_detail_remedio);


                Where where = new Where();
                where.add("receita", ConditionDB.EQUALS, recBean.getId());
                List<ReceitaRemedioBean> recrmList = (List<ReceitaRemedioBean>) dbHelp.select(ReceitaRemedioBean.class, where);

                StringBuilder sql = new StringBuilder();
                sql.append("(");
                String temp = "";
                for (ReceitaRemedioBean recrmBean: recrmList){
                    sql.append(temp);
                    sql.append(recrmBean.getRemedio());
                    temp = ", ";
                }
                sql.append(")");

                where = new Where();
                where.add("id", ConditionDB.IN, sql.toString());

                List<RemedioBean> remList = (List<RemedioBean>) dbHelp.select(RemedioBean.class, where);

                RemedioAdapter adapter = new RemedioAdapter(remList, Activity_usuario.this);

                listView.setAdapter(adapter);

                builder.setView(dialogView);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }

        });


        loadData();
    }

    private void loadData(){

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

        adapter = new ReceitaAdapter(recList, this);

        listView.setAdapter(adapter);
    }

    @Override
    public void finish() {

       setResult(RESULT_OK);

        super.finish();
    }
}
