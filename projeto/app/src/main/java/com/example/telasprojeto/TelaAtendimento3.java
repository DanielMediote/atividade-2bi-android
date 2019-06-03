package com.example.telasprojeto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapters.RemedioAdapter;
import com.example.constants.ConditionDB;
import com.example.db.DBHelper;
import com.example.db.Where;
import com.example.model.beans.MedicoBean;
import com.example.model.beans.PacienteBean;
import com.example.model.beans.PessoaBean;
import com.example.model.beans.ReceitaBean;
import com.example.model.beans.ReceitaRemedioBean;
import com.example.model.beans.RemedioBean;

import java.util.ArrayList;
import java.util.List;

public class TelaAtendimento3 extends AppCompatActivity {

    TextView txtPaciente;
    ListView listView;
    EditText edtObs;
    Button btnSalvar;

    DBHelper dbHelp;

    Integer paciente;
    List<RemedioBean> remedioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_atendimento3);

        txtPaciente = super.findViewById(R.id.txtPaciente);
        listView = super.findViewById(R.id.viewListRemedios);
        edtObs = super.findViewById(R.id.edtObs);
        btnSalvar = super.findViewById(R.id.btnSalvar);

        dbHelp = new DBHelper(this);

        loadData();
    }



    private void loadData(){

        Intent it = getIntent();

        paciente = new Long(it.getLongExtra("pac_id", 0)).intValue();

        PacienteBean pacBean = (PacienteBean) dbHelp.selectById(PacienteBean.class, paciente);
        PessoaBean pesBean = (PessoaBean) dbHelp.selectById(PessoaBean.class, pacBean.getPessoa());

        ArrayList<Integer> remIdList = it.getIntegerArrayListExtra("listItems");

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        String temp = "";
        for (Integer remId : remIdList){
            sb.append(temp);
            sb.append(remId);
            temp = ", ";
        }
        sb.append(")");

        Where where = new Where();
        where.add("id", ConditionDB.IN, sb.toString());

        remedioList = (List<RemedioBean>) dbHelp.select(RemedioBean.class, where);

        RemedioAdapter adapter = new RemedioAdapter(remedioList, this);

        txtPaciente.setText(pesBean.getDescricao());
        listView.setAdapter(adapter);
    }

    public void OnClick(View view){
        if (view.equals(btnSalvar)){
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            Integer pes_id = sp.getInt("idUserLogado", -1);

            Where where = new Where();
            where.add("pessoa", ConditionDB.EQUALS, pes_id);

            List<MedicoBean> medList = (List<MedicoBean>) dbHelp.select(MedicoBean.class, where);
            MedicoBean medBean;
            if (medList.size() > 0){
                medBean = medList.get(0);
            } else {
                medBean = new MedicoBean();
            }

            ReceitaBean recBean = new ReceitaBean();
            recBean.setMedico(medBean.getId());
            recBean.setPaciente(paciente);
            recBean.setObservacao(edtObs.getText().toString());
            recBean.setData(dbHelp.getDataAtual());

            recBean = (ReceitaBean) dbHelp.insert(recBean);

            for (RemedioBean remBean : remedioList){
                ReceitaRemedioBean recmBean = new ReceitaRemedioBean();
                recmBean.setReceita(recBean.getId());
                recmBean.setRemedio(remBean.getId());

                dbHelp.insert(recmBean);
            }

            Toast.makeText(this, "Atendimento salvo com sucesso", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK);
            finish();
        }
    }
}
