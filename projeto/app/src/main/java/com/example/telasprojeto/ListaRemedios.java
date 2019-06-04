package com.example.telasprojeto;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.adapters.MedicoAdapter;
import com.example.adapters.RemedioAdapter;
import com.example.adapters.RemedioTipoAdapter;
import com.example.constants.ConditionDB;
import com.example.constants.Sit;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.MedicoBean;
import com.example.model.beans.PessoaBean;
import com.example.model.beans.RemedioBean;
import com.example.model.beans.RemedioTipoBean;
import com.example.model.dd.PesTpDD;

import java.util.ArrayList;
import java.util.List;

public class ListaRemedios extends AppCompatActivity {

    FloatingActionButton btnAddRemedio;
    ListView remListView;
    SearchView search;

    RemedioAdapter adapter;
    DBHelper dbHelp;

    RemedioBean remBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_remedios);

        btnAddRemedio = super.findViewById(R.id.btnAdd_Remedio);
        remListView = super.findViewById(R.id.listView_remedios);
        search = super.findViewById(R.id.searchView_remedio);

        remListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ListaRemedios.this);

                remBean = (RemedioBean) adapter.getItem(position);

                alert.setTitle("Opções");

                alert.setPositiveButton("Atualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LayoutInflater inflater = ListaRemedios.this.getLayoutInflater();

                        final View view = inflater.inflate(R.layout.dialog_add_remedio, null);

                        RemedioTipoBean remtp = new RemedioTipoBean(-1, Sit.ATIVO, "Tipo");

                        List<RemedioTipoBean> remtpList = new ArrayList<>();
                        remtpList.add(remtp);
                        remtpList.addAll((List<RemedioTipoBean>) dbHelp.select(RemedioTipoBean.class));

                        RemedioTipoAdapter remedioTipoAdapter = new RemedioTipoAdapter(ListaRemedios.this, R.layout.support_simple_spinner_dropdown_item, remtpList);

                        Spinner spinner = view.findViewById(R.id.spinner);
                        spinner.setAdapter(remedioTipoAdapter);

                        AlertDialog.Builder builder = new AlertDialog.Builder(ListaRemedios.this);
                        builder.setTitle("Atualizar remédio");

                        EditText edtDescricao1 = view.findViewById(R.id.rem_descricao);
                        Spinner spinnerRemtp1 = view.findViewById(R.id.spinner);

                        edtDescricao1.setText(remBean.getDescricao());

                        RemedioTipoBean remtBean = (RemedioTipoBean) dbHelp.selectById(RemedioTipoBean.class, remBean.getRemedioTipo());

                        spinnerRemtp1.setSelection(remedioTipoAdapter.getPosition(remtBean));

                        builder.setView(view);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Spinner spinnerRemtp = view.findViewById(R.id.spinner);

                                EditText edtDescricao = view.findViewById(R.id.rem_descricao);
                                Integer remtpId = new Long(spinnerRemtp.getSelectedItemId()).intValue();

//                                remBean = new RemedioBean();
                                remBean.setDescricao(edtDescricao.getText().toString());
                                remBean.setRemedioTipo(remtpId);

                                dbHelp.update(remBean);

                                atualizaLista();
                            }
                        });
                        builder.show();
                    }
                });

                alert.setNegativeButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder alertApagar = new AlertDialog.Builder(ListaRemedios.this);
                        alertApagar.setTitle("Apagar");
                        alertApagar.setMessage("Deseja apagar o remédio " + remBean.getDescricao());

                        alertApagar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelp.delete(remBean);
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


        dbHelp = new DBHelper(this);
        atualizaLista();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Where where = new Where();
                where.add("descricao", ConditionDB.LIKE, search.getQuery().toString());

                atualizaLista(where);

                return false;
            }
        });
    }

    private void atualizaLista(){
        atualizaLista(null);
    }

    private void atualizaLista (Where where) {
        List<RemedioBean> remList;


        if (where != null){
            remList = (List<RemedioBean>) dbHelp.select(RemedioBean.class, where);
        } else {
            remList = (List<RemedioBean>) dbHelp.select(RemedioBean.class);
        }

        adapter = new RemedioAdapter(remList, this);

        remListView.setAdapter(adapter);
    }


    public void onClick(final View v) {
        if (v == btnAddRemedio) {

            LayoutInflater inflater = super.getLayoutInflater();

            final View view = inflater.inflate(R.layout.dialog_add_remedio, null);

            RemedioTipoBean remtp = new RemedioTipoBean(-1, Sit.ATIVO, "Tipo");

            List<RemedioTipoBean> remtpList = new ArrayList<>();
            remtpList.add(remtp);
            remtpList.addAll((List<RemedioTipoBean>) dbHelp.select(RemedioTipoBean.class));

            RemedioTipoAdapter remedioTipoAdapter = new RemedioTipoAdapter(this, R.layout.support_simple_spinner_dropdown_item, remtpList);

            Spinner spinner = view.findViewById(R.id.spinner);
            spinner.setAdapter(remedioTipoAdapter);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Inserir remédio");

            builder.setView(view);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Spinner spinnerRemtp = view.findViewById(R.id.spinner);

                    EditText edtDescricao = view.findViewById(R.id.rem_descricao);
                    Integer remtpId = new Long(spinnerRemtp.getSelectedItemId()).intValue();

                    RemedioBean remBean = new RemedioBean();
                    remBean.setDescricao(edtDescricao.getText().toString());
                    remBean.setRemedioTipo(remtpId);

                    dbHelp.insert(remBean);

                    atualizaLista();
                }
            });
            builder.show();
        }
    }

}
