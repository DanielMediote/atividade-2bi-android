package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.db.DBHelper;
import com.example.model.beans.ReceitaBean;
import com.example.model.beans.RemedioBean;
import com.example.telasprojeto.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ReceitaAdapter extends BaseAdapter {

    private final List<ReceitaBean> list;
    private Context context;

    public ReceitaAdapter(List<ReceitaBean> list, Context context){
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

        ReceitaBean recBean = (ReceitaBean) DBhelp.selectById(ReceitaBean.class, new Long(getItemId(position)).intValue());

        View view = act.getLayoutInflater()
                .inflate(R.layout.lis_view_layout, parent, false);

        TextView text = view.findViewById(R.id.descricao);
        text.setText(new SimpleDateFormat("dd/MM/yyyy").format(recBean.getData()));
        return view;
    }
}
