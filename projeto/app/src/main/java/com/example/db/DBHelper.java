package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends DB {

    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context);
        db = super.getWritableDatabase();
    }
}
