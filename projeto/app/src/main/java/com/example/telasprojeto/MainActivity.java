package com.example.telasprojeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.constants.ConditionDB;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.Bean;
import com.example.model.beans.PessoaBean;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    DBHelper dbHelper;
    EditText fieldLogin;
    EditText fieldSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldLogin = super.findViewById(R.id.edt_usuario);
        fieldSenha = super.findViewById(R.id.edt_senha);

        dbHelper = new DBHelper(this);

    }

    public void onClick(View v){
        if(v.getId() == R.id.btnEntrar){

            String login = fieldLogin.getText().toString();
            String senha = fieldSenha.getText().toString();

            Where where = new Where();
            where.add("login", ConditionDB.EQUALS, login);
            where.add("senha", ConditionDB.EQUALS, senha);

            List<Bean> list = dbHelper.select(PessoaBean.class, where);

            if (list.size() > 0) {
                Toast.makeText(this, "USUARIO LOGADO", Toast.LENGTH_LONG).show();

                Intent it = new Intent(this, TelaPrincipal.class);
                super.startActivity(it);
            } else {
                Toast.makeText(this, "USUARIO OU SENHA INVÁLIDOS", Toast.LENGTH_LONG).show();
            }
        }
    }
}
