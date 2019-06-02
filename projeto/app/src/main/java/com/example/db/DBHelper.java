package com.example.db;

import android.content.ContentValues;
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
                if (field.isAnnotationPresent(Column.class) == false) continue;

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
                    Field field = clazz.getDeclaredField(key);
                    Column column = field.getAnnotation(Column.class);

                    sb.append(temp);
                    sb.append(column.name());
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
                            if (field.isAnnotationPresent(Column.class) == false) continue;

                            Column column = field.getAnnotation(Column.class);
                            if (column.name().equals(columnName)) {
                                field.setAccessible(true);
                                setValue(bean, field, cursor, cursor.getColumnIndex(columnName));
                                field.setAccessible(false);
                            }
                        }
                    }
                    result.add(bean);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            Log.e("SELECT", "erro ao efetuar o select");
        } finally {
        }

        return result;
    }

    public void insert(Bean bean) {
        try {
            Table table = bean.getClass().getAnnotation(Table.class);

            ContentValues values = new ContentValues();
            Class<? extends Bean> clazz = bean.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class) == false) continue;

                field.setAccessible(true);
                Object obj = field.get(bean);
                field.setAccessible(false);


                Column column = field.getAnnotation(Column.class);

                if (field.getType().equals(Integer.class)) {
                    values.put(column.name(), (Integer) obj);
                } else if (field.getType().equals(String.class)) {
                    values.put(column.name(), (String) obj);
                }
            }
            db.insertOrThrow(table.name(), null, values);
        } catch (Exception e){
            Log.e("INSERT", "erro ao efetuar o insert");
        }
    }


    public void deleteAll(Class<? extends Bean> clazz){
        Table table = clazz.getAnnotation(Table.class);
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(table.name());

        db.execSQL(sb.toString());
    }




    private static void setValue(Object obj, Field field, Cursor cursor, Integer index) throws IllegalAccessException {
        if (field.getType().equals(Integer.class)) {
            field.set(obj, cursor.getInt(index));
        } else if (field.getType().equals(String.class)) {
            field.set(obj, cursor.getString(index));
        }
    }

}
