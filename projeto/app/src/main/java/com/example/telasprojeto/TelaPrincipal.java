package com.example.telasprojeto;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

public class TelaPrincipal extends AppCompatActivity implements View.OnClickListener{

    Button btnMedico, btnRemedios, btnPacientes, btnAtendimento;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        btnMedico = super.findViewById(R.id.btnMedico);
        btnRemedios = super.findViewById(R.id.btnRemedios);
        btnPacientes = super.findViewById(R.id.btnPacientes);
        btnAtendimento = super.findViewById(R.id.btnAtendimento);
    }

    //EVENTO ONCLICK PARA OS BOTÕES DA TELA PRINCIPAL
    public void onClick(View v){
        if(v.getId() == R.id.btnMedico){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.btnMedico), R.string.undo_medicos, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if(v.getId() == R.id.btnRemedios){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.btnRemedios), R.string.undo_remedios, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if(v.getId() == R.id.btnPacientes){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.btnPacientes), R.string.undo_pacientes, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else{
            Snackbar snackbar = Snackbar.make(findViewById(R.id.btnAtendimento), R.string.undo_atendimento, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
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
