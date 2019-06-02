package com.example.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.db.DBHelper;
import com.example.model.beans.RemedioBean;
import com.example.model.beans.RemedioTipoBean;
import com.example.telasprojeto.R;

import java.util.ArrayList;
import java.util.List;

public class RemedioTipoAdapter extends ArrayAdapter<RemedioTipoBean> {

//    private final List<RemedioTipoBean> list;
    private Context context;

    public RemedioTipoAdapter(Context context, int resource) {
        super(context, resource);
    }

    public RemedioTipoAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public RemedioTipoAdapter(Context context, int resource, RemedioTipoBean[] objects) {
        super(context, resource, objects);
    }

    public RemedioTipoAdapter(Context context, int resource, int textViewResourceId, RemedioTipoBean[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public RemedioTipoAdapter(Context context, int resource, List<RemedioTipoBean> objects) {
        super(context, resource, objects);
    }

    public RemedioTipoAdapter(Context context, int resource, int textViewResourceId, List<RemedioTipoBean> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public boolean isEnabled(int position){

        if(position == 0){
            // Disabilita a primeira posição (hint)
            return false;

        } else {
            return true;
        }
    }


    @Override
    public long getItemId(int position) {
        return super.getItem(position).getId();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view = super.getDropDownView(position, convertView, parent);

        TextView tv = (TextView) view;
        tv.setText(getItem(position).getDescricao());

        if(position == 0){
            tv.setTextColor(Color.GRAY);

        }else {
            tv.setTextColor(Color.BLACK);
        }

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  super.getView(position, convertView, parent);

        TextView tv = (TextView) view;

        tv.setText(getItem(position).getDescricao());

        return view;
    }
}

