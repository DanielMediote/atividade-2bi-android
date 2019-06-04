package com.example.telasprojeto;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.adapters.MedicoAdapter;
import com.example.adapters.PacienteAdapter;
import com.example.constants.ConditionDB;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.MedicoBean;
import com.example.model.beans.PacienteBean;
import com.example.model.beans.PessoaBean;
import com.example.model.dd.PesTpDD;

import java.util.List;

public class ListaPacientes extends AppCompatActivity {

    FloatingActionButton btnAddPaciente;
    ListView listPacientes;
    SearchView search;
    DBHelper dbHelp;

    PacienteAdapter adapter;
    PacienteBean pacBean;
    PessoaBean pesBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        btnAddPaciente = super.findViewById(R.id.fbtnAdd_paciente);
        listPacientes = super.findViewById(R.id.listaView_paciente);
        search = super.findViewById(R.id.searchView_pacientes);

        dbHelp = new DBHelper(this);

        atualizaLista();

        listPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pacBean = (PacienteBean) adapter.getItem(position);

                AlertDialog.Builder alert = new AlertDialog.Builder(ListaPacientes.this);
                alert.setTitle("Opção");
                alert.setPositiveButton("Atualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LayoutInflater inflater = ListaPacientes.super.getLayoutInflater();

                        final View view = inflater.inflate(R.layout.dialog_add_paciente, null);

                        AlertDialog.Builder builder = new AlertDialog.Builder(ListaPacientes.this);
                        builder.setTitle("Atualizar paciente");

                        pesBean = (PessoaBean) dbHelp.selectById(PessoaBean.class, pacBean.getPessoa());

                        EditText edtNome1 = view.findViewById(R.id.pac_nome);
                        EditText edtCpf1 = view.findViewById(R.id.pac_cpf);
                        EditText edtSenha1 = view.findViewById(R.id.pac_password);

                        edtNome1.setText(pesBean.getDescricao());
                        edtCpf1.setText(pesBean.getLogin());
                        edtSenha1.setText(pesBean.getSenha());

                        builder.setView(view);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText edtNome = view.findViewById(R.id.pac_nome);
                                EditText edtCpf = view.findViewById(R.id.pac_cpf);
                                EditText edtSenha = view.findViewById(R.id.pac_password);

                                pesBean.setDescricao(edtNome.getText().toString());
                                pesBean.setLogin(edtCpf.getText().toString());
                                pesBean.setSenha(edtSenha.getText().toString());
                                pesBean.setPessoaTipo(PesTpDD.PACIENTE);

                                dbHelp.update(pesBean);

                                pacBean = new PacienteBean();
                                pacBean.setPessoa(pesBean.getId());
                                pacBean.setCpf(edtCpf.getText().toString());

                                dbHelp.update(pacBean);

                                atualizaLista();
                            }
                        });

                        builder.show();
                    }
                });
                alert.setNegativeButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder alertApagar = new AlertDialog.Builder(ListaPacientes.this);

                        PessoaBean pesBean = (PessoaBean) dbHelp.selectById(PessoaBean.class, pacBean.getPessoa());

                        alertApagar.setTitle("Apagar");
                        alertApagar.setMessage("Deseja apagar o paciente " + pesBean.getDescricao());

                        alertApagar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelp.delete(pacBean);
                                atualizaLista();
                            }
                        });
                        alertApagar.setNegativeButton("Não", null);
                        alertApagar.show();
                    }
                });
                alert.setNeutralButton("Cancelar", null);
                alert.show();
            }
        });


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Where where = new Where();
                where.add("descricao", ConditionDB.LIKE, search.getQuery().toString());
                where.add("pessoaTipo", ConditionDB.EQUALS, PesTpDD.PACIENTE);

                List<PessoaBean> list = (List<PessoaBean>) dbHelp.select(PessoaBean.class, where);

                StringBuilder sb = new StringBuilder();
                sb.append("(");
                String temp = "";
                for (PessoaBean pes : list){
                    sb.append(temp);
                    sb.append(pes.getId());
                    temp = ", ";
                }
                sb.append(")");

                where = new Where();
                where.add("pessoa", ConditionDB.IN, sb.toString());

                atualizaLista(where);
                return false;
            }
        });

    }

    private void atualizaLista(){
        atualizaLista(null);
    }

    private void atualizaLista (Where where) {
        List<PacienteBean> pacList;

        if (where != null){
            pacList = (List<PacienteBean>) dbHelp.select(PacienteBean.class, where);
        } else {
            pacList = (List<PacienteBean>) dbHelp.select(PacienteBean.class);
        }

        adapter = new PacienteAdapter(pacList, this);

        listPacientes.setAdapter(adapter);
    }


    public void onClick(View v) {
        if (v == btnAddPaciente) {
            final DBHelper dbHelp = new DBHelper(this);

            LayoutInflater inflater = super.getLayoutInflater();

            final View view = inflater.inflate(R.layout.dialog_add_paciente, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Inserir paciente");

            builder.setView(view);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText edtNome = view.findViewById(R.id.pac_nome);
                    EditText edtCpf = view.findViewById(R.id.pac_cpf);
                    EditText edtSenha = view.findViewById(R.id.pac_password);

                    PessoaBean pesBean = new PessoaBean();
                    pesBean.setDescricao(edtNome.getText().toString());
                    pesBean.setLogin(edtCpf.getText().toString());
                    pesBean.setSenha(edtSenha.getText().toString());
                    pesBean.setPessoaTipo(PesTpDD.PACIENTE);

                    pesBean = (PessoaBean) dbHelp.insert(pesBean);

                    PacienteBean pacBean = new PacienteBean();
                    pacBean.setPessoa(pesBean.getId());
                    pacBean.setCpf(edtCpf.getText().toString());

                    dbHelp.insert(pacBean);

                    atualizaLista();
                }
            });

            builder.show();
        }
    }

}
