package com.example.telasprojeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity implements View.OnClickListener{
    Button btnMedico, btnRemedios, btnPacientes, btnAtendimento;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        btnMedico = super.findViewById(R.id.btnMedico);
        btnRemedios = super.findViewById(R.id.btnRemedios);
        btnPacientes = super.findViewById(R.id.btnAtendimento);
        btnAtendimento = super.findViewById(R.id.btnAtendimento);
    }

    //EVENTO ONCLICK PARA OS BOTÕES DA TELA PRINCIPAL
    public void onClick(View v){
        Intent it;
        if(v.getId() == R.id.btnMedico){
            it = new Intent(this, ListaMedicos.class);
        }else if(v.getId() == R.id.btnRemedios){
            it = new Intent(this, ListaRemedios.class);
        }else if(v.getId() == R.id.btnAtendimento){
            it = new Intent(this, TelaAtendimento.class);
        }else{
            it = new Intent(this, ListaPacientes.class);
        }
        startActivity(it);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.itemSair:
                Toast.makeText(this, "SAIU COM SUCESSO", Toast.LENGTH_SHORT).show();
                super.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
