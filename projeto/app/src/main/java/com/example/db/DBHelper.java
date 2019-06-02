package com.example.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.annotations.Column;
import com.example.annotations.Table;
import com.example.model.beans.Bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends DB {

    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context);
        db = super.getWritableDatabase();
    }


    public List<Bean> select(Class<? extends Bean> clazz) {
        return  select(clazz, null);
    }

    public List<Bean> select(Class<? extends Bean> clazz, Where where){
        List<Bean> result = new ArrayList<Bean>();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" SELECT ");

            String temp = "";
            for (Field field : clazz.getDeclaredFields()) {
                Column column = field.getAnnotation(Column.class);
                sb.append(temp);
                sb.append(column.name());

                temp = ", ";
            }

            Table table = clazz.getAnnotation(Table.class);
            sb.append(" FROM ");
            sb.append(table.name());

            if (where != null){
                sb.append(" WHERE ");

                temp = "";
                for (String key : where.getItems().keySet()){
                    Field field = clazz.getField(key);
                    Column column = field.getAnnotation(Column.class);

                    sb.append(temp);
                    sb.append("lower(");
                    sb.append(column.name());
                    sb.append(")");
                    sb.append(where.getItems().get(key));

                    temp = " AND ";
                }

            }

            Cursor cursor;
            cursor = db.rawQuery(sb.toString(), null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Bean bean = clazz.newInstance();

                    for (String columnName : cursor.getColumnNames()) {
                        for (Field field : clazz.getDeclaredFields()) {
                            Column column = field.getAnnotation(Column.class);
                            if (column.name().equals(columnName)) {
                                field.setAccessible(true);
                                setValue(field, cursor, cursor.getColumnIndex(columnName));
                                field.setAccessible(false);
                            }
                        }
                    }
                    result.add(bean);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            Log.e("SELECT", "erro ao efetuar o select");
        }

        return result;
    }

    private static void setValue(Field field, Cursor cursor, Integer index) throws IllegalAccessException {
        if (field.getType().equals(Integer.class)) {
            field.set(null, cursor.getInt(index));
        } else if (field.getType().equals(String.class)) {
            field.set(null, cursor.getString(index));
        }
    }

}
