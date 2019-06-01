package com.example.telasprojeto;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.db.DBHelper;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelp;
        try {
            dbHelp = new DBHelper(this);
        } catch (SQLiteException e){
            Toast.makeText(this, "Falha na conexão!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    public void onClick(View v){
        if(v.getId() == R.id.btnEntrar){
            Intent it = new Intent(this, TelaPrincipal.class);
            super.startActivity(it);
        }
    }
}
