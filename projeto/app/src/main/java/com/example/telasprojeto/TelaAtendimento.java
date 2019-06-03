package com.example.telasprojeto;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TelaAtendimento extends AppCompatActivity {

    ListView listaAtendimento;
    Button btnProximo, btnVoltar;
    ArrayAdapter<String> arrayAdapterPacientes, arrayAdapterRemedios;
    Integer etapaAtendimento = 0;

    //TESTE
    String[] pacientes = {"Paciente 1", "Paciente 2", "Paciente 3"};
    String[] remedios = {"Remedio 1", "Remedio 2", "Remedio 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atendimento);

        btnProximo = findViewById(R.id.btnProximo);
        btnVoltar = findViewById(R.id.btnVoltar);
        listaAtendimento = findViewById(R.id.listView_Atendimento);

        arrayAdapterPacientes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pacientes);
        arrayAdapterRemedios = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, remedios);

        //AO CLICK MUDAR A CORD DO ITEM SELECIONADO
        listaAtendimento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int j = 0; j < parent.getChildCount(); j++)
                    parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
                view.setBackgroundColor(Color.parseColor("#803D5AFE"));
            }
        });

        listaAtendimento.setAdapter(arrayAdapterPacientes);
        btnVoltar.setVisibility(View.GONE);
    }

    public void onClick(View v){
        if (v == btnProximo){
            if (etapaAtendimento == 0){
                btnVoltar.setVisibility(View.VISIBLE);
                listaAtendimento.setAdapter(arrayAdapterRemedios);
            }
            etapaAtendimento++;
        }else if(v == btnVoltar){
            if (etapaAtendimento == 1){
                btnVoltar.setVisibility(View.GONE);
                listaAtendimento.setAdapter(arrayAdapterPacientes);
            }
            etapaAtendimento--;
        }else{

        }
    }


}
