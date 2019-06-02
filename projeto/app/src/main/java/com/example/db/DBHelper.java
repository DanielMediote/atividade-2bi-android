package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.annotations.Column;
import com.example.annotations.PrimaryKey;
import com.example.annotations.Table;
import com.example.constants.ConditionDB;
import com.example.model.beans.Bean;
import com.example.model.beans.PessoaBean;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends DB {

    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context);
        db = super.getWritableDatabase();
    }


    public List<? extends Object> select(Class<? extends Bean> clazz) {
        return  select(clazz, null);
    }

    public List<? extends Object> select(Class<? extends Bean> clazz, Where where){
        List<Object> result = new ArrayList<Object>();
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

            Cursor cursor;
            if (where != null){
                sb.append(" WHERE ");

                temp = "";
                String[] args = new String[where.getItems().size()];
                int i = 0;
                for (String key : where.getItems().keySet()){
                    Field field = clazz.getDeclaredField(key);
                    Column column = field.getAnnotation(Column.class);

                    sb.append(temp);
                    sb.append(column.name()+" ");
                    sb.append(where.getItems().get(key).getCondition().value);
                    sb.append(" ? ");

                    args[i] = where.getItems().get(key).getValue().toString();

                    temp = "AND ";
                    i++;
                }
                cursor = db.rawQuery(sb.toString(), args);
            } else {
                cursor = db.rawQuery(sb.toString(), null);
            }

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
//                    convert(clazz, bean);
                    result.add(bean);
                } while (cursor.moveToNext());
            }

        }catch (Exception e){
            Log.e("SELECT", "erro ao efetuar o select");
            e.printStackTrace();
        }

        return result;
    }

    public Object insert(Bean bean) {
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
                } else if (field.getType().equals(Date.class)){
                    if (obj != null) values.put(column.name(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) obj));
                }
            }
            Integer ultimoId = new Long(db.insertOrThrow(table.name(), null, values)).intValue();
            return selectById(clazz, ultimoId);

        } catch (Exception e){
            Log.e("INSERT", "erro ao efetuar o insert");
            e.printStackTrace();
            return null;
        }
    }


    public Object selectById(Class<? extends Bean> clazz, Integer id){
        Where where = new Where();
        where.add("id", ConditionDB.EQUALS, id);

        List<? extends Bean> list = (List<? extends Bean>) select(clazz, where);
        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }


    public void update(Bean bean) {
        try {
            Class<? extends Bean> clazz = bean.getClass();
            Table table = clazz.getAnnotation(Table.class);


            Where where = new Where();
            ContentValues values = new ContentValues();
            for (Field field : clazz.getDeclaredFields()) {
                boolean acessivel = field.isAccessible();
                field.setAccessible(true);

                if (field.isAnnotationPresent(Column.class) == false) continue;
                Column column = field.getAnnotation(Column.class);
                Object obj = field.get(bean);
                if (field.getType().equals(Integer.class)) {
                    values.put(column.name(), (Integer) obj);
                } else if (field.getType().equals(String.class)) {
                    values.put(column.name(), (String) obj);
                } else if (field.getType().equals(Date.class)){
                    if (obj != null) values.put(column.name(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) obj));
                }


                if (field.isAnnotationPresent(PrimaryKey.class) == false) continue;

                where.add(column.field(), ConditionDB.EQUALS, field.get(bean));
                field.setAccessible(acessivel);
            }

            if (where != null) {
                String[] args = new String[where.getItems().size()];
                StringBuilder sb = new StringBuilder();
                String temp = "";
                int i = 0;
                for (String key : where.getItems().keySet()) {
                    Field field = clazz.getDeclaredField(key);
                    Column column = field.getAnnotation(Column.class);

                    sb.append(temp);
                    sb.append(column.name() + " ");
                    sb.append(where.getItems().get(key).getCondition().value);
                    sb.append(" ? ");

                    args[i] = where.getItems().get(key).getValue().toString();

                    temp = "AND ";
                    i++;
                }
                db.update(table.name(), values, sb.toString(), args);
            } else {
                db.update(table.name(), values, null, null);
            }
        } catch (Exception e){
            Log.e("UPDATE", "erro ao efetuar o update");
            e.printStackTrace();
        }
    }


    public void deleteAll(Class<? extends Bean> clazz){
        Table table = clazz.getAnnotation(Table.class);
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(table.name());

        db.execSQL(sb.toString());
    }


    private static void setValue(Object obj, Field field, Cursor cursor, Integer index) throws IllegalAccessException, ParseException {
        if (field.getType().equals(Integer.class)) {
            field.set(obj, cursor.getInt(index));
        } else if (field.getType().equals(String.class)) {
            field.set(obj, cursor.getString(index));
        } else if (field.getType().equals(Date.class)){
            String value = cursor.getString(index);
            if (value != null) field.set(obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cursor.getString(index)));
        }
    }

    public Date getDataAtual(){
        return new Date();
    }


}
