package com.example.telasprojeto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.constants.ConditionDB;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.PessoaBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    EditText fieldLogin;
    EditText fieldSenha;
    Switch lembrarUser;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldLogin = super.findViewById(R.id.edt_usuario);
        fieldSenha = super.findViewById(R.id.edt_senha);
        lembrarUser = super.findViewById(R.id.lembrar_usuario);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        fieldLogin.setText(sp.getString("userLogin", ""));
        fieldSenha.setText(sp.getString("userSenha", ""));

    }

    public void onClick(View v){
        DBHelper dbHelper = new DBHelper(this);
        if(v.getId() == R.id.btnEntrar){

            String login = fieldLogin.getText().toString();
            String senha = fieldSenha.getText().toString();

            Where where = new Where();
            where.add("login", ConditionDB.EQUALS, login);
            where.add("senha", ConditionDB.EQUALS, senha);

            List<PessoaBean> list = (List<PessoaBean>) dbHelper.select(PessoaBean.class, where);

            if (list.size() > 0) {
                PessoaBean pesBean = list.get(0);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sp.edit();

                if (lembrarUser.isChecked()){
                    editor.putString("userLogin", login);
                    editor.putString("userSenha", senha);
                    editor.apply();
                } else{
                    editor.remove("userLogin");
                    editor.remove("userSenha");
                }
                editor.putInt("idUserLogado", pesBean.getId());
                editor.apply();

                if (pesBean.getDataUltimoLogin() != null) {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    String dataUltimoLogin = format.format(pesBean.getDataUltimoLogin());
                    format = new SimpleDateFormat("HH:mm");
                    String horaUltimoLogin = format.format(pesBean.getDataUltimoLogin());

                    Toast.makeText(this, "Ultimo login dia "+dataUltimoLogin+" às "+horaUltimoLogin, Toast.LENGTH_LONG).show();
                }

                pesBean.setDataUltimoLogin(dbHelper.getDataAtual());
                dbHelper.update(pesBean);

                Intent it = new Intent(this, TelaPrincipal.class);
                super.startActivity(it);
            } else {
                Toast.makeText(this, "USUARIO OU SENHA INVÁLIDOS", Toast.LENGTH_LONG).show();
            }
        }
    }
}
