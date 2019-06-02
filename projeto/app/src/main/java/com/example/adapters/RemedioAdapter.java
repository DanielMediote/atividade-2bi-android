package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.db.DBHelper;
import com.example.model.beans.MedicoBean;
import com.example.model.beans.PessoaBean;
import com.example.model.beans.RemedioBean;
import com.example.telasprojeto.R;

import java.util.List;

public class RemedioAdapter extends BaseAdapter {

    private final List<RemedioBean> list;
    private Context context;

    public RemedioAdapter(List<RemedioBean> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity act = (Activity) context;
        DBHelper DBhelp = new DBHelper(context);

        RemedioBean remBean = (RemedioBean) DBhelp.selectById(RemedioBean.class, new Long(getItemId(position)).intValue());

        View view = act.getLayoutInflater()
                .inflate(R.layout.lis_view_layout, parent, false);

        TextView text = view.findViewById(R.id.descricao);
        text.setText(remBean.getDescricao());
        return view;
    }
}
