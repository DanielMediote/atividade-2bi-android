package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    private static final String DB_NAME = "farmacia";
    private static final Integer DB_VERSION = 1;


    public DB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createScriptCreateTables());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private static String createScriptCreateTables(){
        StringBuilder sb = new StringBuilder();
        sb.append(createScriptTablePessoa());
        sb.append(createScriptTableMedico());
        sb.append(createScriptTableRemedioTipo());
        sb.append(createScriptTablePaciente());
        sb.append(createScriptTableRemedio());
        sb.append(createScriptTableReceita());
        sb.append(createScriptTableReceitaRemedio());

        return sb.toString();
    }


    private static String createScriptTablePessoa(){
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE pessoa(                            ");
        sb.append("     pes_id INTEGER PRIMARY KEY AUTOINCREMENT,   ");
        sb.append("     pes_descricao TEXT,                         ");
        sb.append("     pes_login TEXT,                             ");
        sb.append("     pes_senha                                   ");
        sb.append(" );                                               ");

        return sb.toString();
    }

    private static String createScriptTableMedico(){
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE medico(                                ");
        sb.append("     med_id INTEGER PRIMARY KEY AUTOINCREMENT,       ");
        sb.append("     med_crm TEXT                                    ");
        sb.append("     pes_id INTEGER,                                 ");
        sb.append("     FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)  ");
        sb.append(" );                                                   ");

        return sb.toString();
    }

    private static String createScriptTablePaciente(){
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE paciente(                              ");
        sb.append("     pac_id INTEGER PRIMARY KEY AUTOINCREMENT,       ");
        sb.append("     pac_cpf TEXT,                                   ");
        sb.append("     pes_id INTEGER,                                 ");
        sb.append("     FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)  ");
        sb.append(" );                                                   ");

        return sb.toString();
    }

    private static String createScriptTableRemedioTipo(){
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE remediotipo(                       ");
        sb.append("     remt_id INTEGER PRIMARY KEY AUTOINCREMENT,  ");
        sb.append("     remt_ativo INTEGER,                         ");
        sb.append("     remt_descricao TEXT                         ");
        sb.append(" );                                               ");

        return sb.toString();
    }


    private static String createScriptTableRemedio(){
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE remedio(                                       ");
        sb.append("     rem_id  INTEGER PRIMARY KEY AUTOINCREMENT,              ");
        sb.append("     rem_descricao TEXT,                                     ");
        sb.append("     remt_id INTEGER,                                        ");
        sb.append("     FOREIGN KEY (remt_id) REFERENCES remediotipo(remt_id)   ");
        sb.append(" );                                                           ");

        return sb.toString();
    }


    private static String createScriptTableReceita(){
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE receita(");
        sb.append("     rec_id  INTEGER PRIMARY KEY AUTOINCREMENT,       ");
        sb.append("     med_id INTEGER,                                  ");
        sb.append("     pac_id INTEGER,                                  ");
        sb.append("     rec_observacao TEXT                              ");
        sb.append("     FOREIGN KEY (med_id) REFERENCES medico(med_id)   ");
        sb.append("     FOREIGN KEY (pac_id) REFERENCES paciente(pac_id) ");
        sb.append(" );                                                    ");

        return sb.toString();
    }

    private static String createScriptTableReceitaRemedio(){
        StringBuilder sb = new StringBuilder();
        sb.append(" CREATE TABLE receitaremedio(");
        sb.append("     recrem_id  INTEGER PRIMARY KEY AUTOINCREMENT,   ");
        sb.append("     rec_id INTEGER,                                 ");
        sb.append("     recrem_observacao TEXT,                         ");
        sb.append("     FOREIGN KEY (rec_id) REFERENCES receita(rec_id) ");
        sb.append(" );                                                   ");

        return sb.toString();
    }
}
