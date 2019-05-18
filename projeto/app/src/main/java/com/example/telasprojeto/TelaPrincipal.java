package com.example.telasprojeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

    }

    public void onClick(View v){
        if (v.getId() == R.id.btn_cadastrar){
            Intent it = new Intent(this, TelaCadastro.class);
            super.startActivity(it);
        }
    }
}
