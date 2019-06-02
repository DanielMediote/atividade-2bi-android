package com.example.telasprojeto;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.db.DBHelper;
import com.example.db.DefaultDataHelper;

public class SplashScreen extends AppCompatActivity{

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = super.findViewById(R.id.progressBar);

        new ExecStarterDB(this).execute();
    }

    private void mostrarMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.finish();
    }

    private void exibirProgress(boolean exibir) {
        progressBar.setVisibility   (exibir ? View.VISIBLE : View.GONE);
    }


    class ExecStarterDB extends AsyncTask<Object, Void, String>{

        Context context;

        ExecStarterDB(Context context){
            super();
            this.context = context;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgress(true);
        }

        @Override
        protected String doInBackground(Object... objects) {
            new DefaultDataHelper(context);

            return null;
        }

        @Override
        protected void onPostExecute(String object) {
            super.onPostExecute(object);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    mostrarMainActivity();
                }
            }, 1000);

        }
    }
}
