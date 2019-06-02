package com.example.telasprojeto;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.MedicoAdapter;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.MedicoBean;
import com.example.model.beans.PessoaBean;
import com.example.model.dd.PesTpDD;

import java.util.List;

public class ListaMedicos extends AppCompatActivity {

    FloatingActionButton btnAddMedicos;
    ListView listMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);

        listMedicos = super.findViewById(R.id.listView_medicos);
        btnAddMedicos = findViewById(R.id.btnAdd_medicos);

        atualizaLista();
    }


    private void atualizaLista () {
        DBHelper DBHelp = new DBHelper(this);
        List<MedicoBean> medList = (List<MedicoBean>) DBHelp.select(MedicoBean.class);

        MedicoAdapter adapter = new MedicoAdapter(medList, this);

        listMedicos.setAdapter(adapter);
    }

    public void onClick(View v) {
        if (v == btnAddMedicos) {
            final DBHelper dbHelp = new DBHelper(this);

            LayoutInflater inflater = super.getLayoutInflater();

            final View view = inflater.inflate(R.layout.dialog_add_medico, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Inserir médico");

            builder.setView(view);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText edtNome = view.findViewById(R.id.med_nome);
                    EditText edtCrm = view.findViewById(R.id.med_crm);
                    EditText edtSenha = view.findViewById(R.id.med_password);

                    PessoaBean pesBean = new PessoaBean();
                    pesBean.setDescricao(edtNome.getText().toString());
                    pesBean.setLogin(edtCrm.getText().toString());
                    pesBean.setSenha(edtSenha.getText().toString());
                    pesBean.setPessoaTipo(PesTpDD.MEDICO);

                    pesBean = (PessoaBean) dbHelp.insert(pesBean);

                    MedicoBean medBean = new MedicoBean();
                    medBean.setPessoa(pesBean.getId());
                    medBean.setCrm(edtCrm.getText().toString());

                    dbHelp.insert(medBean);

                    atualizaLista();
                }
            });

            builder.show();
        }
    }

}

