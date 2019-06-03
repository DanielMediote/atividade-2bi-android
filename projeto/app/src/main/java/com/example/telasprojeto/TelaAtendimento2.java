package com.example.telasprojeto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adapters.RemedioAdapter;
import com.example.adapters.RemedioAdapterMulpleChoice;
import com.example.adapters.RemedioTipoAdapter;
import com.example.adapters.holders.ListViewItemViewHolder;
import com.example.constants.ConditionDB;
import com.example.constants.Sit;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.RemedioBean;
import com.example.model.beans.RemedioTipoBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TelaAtendimento2 extends AppCompatActivity {

    ListView remListView;
    SearchView search;

    Button btnVoltar, btnProximo;

    DBHelper dbHelp;
    Map<Integer, RemedioBean> itemsSelecionados;
    RemedioAdapterMulpleChoice adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atendimento2);

        remListView = super.findViewById(R.id.listView_remedios);
        search = super.findViewById(R.id.searchView_remedio);
        btnVoltar = super.findViewById(R.id.btnVoltar);
        btnProximo = super.findViewById(R.id.btnProximo);

        itemsSelecionados = new LinkedHashMap<>();

        dbHelp = new DBHelper(this);
        atualizaLista();

        remListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItemViewHolder viewHolder = (ListViewItemViewHolder) view.getTag();

                CheckBox check = viewHolder.getItemCheckBox();

                if (check.isChecked() == false){
                    RemedioBean remBean = (RemedioBean) adapter.getItem(position);
                    itemsSelecionados.put(new Long(id).intValue(), remBean);
                    check.setChecked(true);
                } else {
                    if (itemsSelecionados.containsKey(new Long(id).intValue())){
                        itemsSelecionados.remove(new Long(id).intValue());
                    }
                    check.setChecked(false);
                }
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

        adapter = new RemedioAdapterMulpleChoice(remList, this);

        remListView.setAdapter(adapter);
    }

    public void onClick(View view){
        if (view.equals(btnProximo)){

            Intent it = getIntent();
            Long pacId = it.getLongExtra("pac_id", 0);

            it = new Intent(this, TelaAtendimento3.class);
            it.putExtra("pac_id", pacId);
            it.putIntegerArrayListExtra("listItems", new ArrayList<>(itemsSelecionados.keySet()));

            startActivityForResult(it, 90);

        } else if (view.equals(btnVoltar)){
            super.finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 90:
                if (resultCode == RESULT_OK){
                    setResult(RESULT_OK);
                    finish();
                }
                break;
        }
    }
}
