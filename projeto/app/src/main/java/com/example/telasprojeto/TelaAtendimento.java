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
    ArrayAdapter<String> arrayAdapter;

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

        //CARREGAR OS PACIENTES NA LISTVIEW
        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, pacientes);

        listaAtendimento.setAdapter(arrayAdapter);

        btnVoltar.setVisibility(View.GONE);
    }

    public void onClick(View v){
        if (v == btnProximo){
            btnVoltar.setVisibility(View.VISIBLE);

            //CARREGR OS REMEDIOS NA LISTVIEW
            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, remedios);
            listaAtendimento.setAdapter(arrayAdapter);
        }else if(v == btnVoltar){

        }else{

        }

    }


}
