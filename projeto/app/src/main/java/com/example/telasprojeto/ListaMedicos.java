package com.example.telasprojeto;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ListaMedicos extends AppCompatActivity {

    FloatingActionButton btnAddMedicos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);

        btnAddMedicos = findViewById(R.id.fbtnAdd_medicos);
    }

    public void onClick(View v) {
        if (v == btnAddMedicos) {

        }
    }

}

