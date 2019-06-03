package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.adapters.holders.ListViewItemViewHolder;
import com.example.db.DBHelper;
import com.example.model.beans.MedicoBean;
import com.example.model.beans.PessoaBean;
import com.example.model.beans.RemedioBean;
import com.example.telasprojeto.R;

import java.util.List;

public class RemedioAdapterMulpleChoice extends BaseAdapter {

    private final List<RemedioBean> list;
    private Context context;

    public RemedioAdapterMulpleChoice(List<RemedioBean> list, Context context){
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
        DBHelper DBhelp = new DBHelper(context);

        RemedioBean remBean = (RemedioBean) DBhelp.selectById(RemedioBean.class, new Long(getItemId(position)).intValue());

        ListViewItemViewHolder viewHolder;
        if (convertView != null){
            viewHolder = (ListViewItemViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(context, R.layout.list_view_layout_choice_multiple, null);
            CheckBox check = convertView.findViewById(R.id.list_view_item_checkbox);
            TextView txt = convertView.findViewById(R.id.list_view_item_text);

            viewHolder = new ListViewItemViewHolder(convertView);
            viewHolder.setItemCheckBox(check);
            viewHolder.setItemTextView(txt);

            convertView.setTag(viewHolder);
        }

        viewHolder.getItemTextView().setText(remBean.getDescricao());
        return convertView;
    }
}
