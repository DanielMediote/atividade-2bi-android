package com.example.telasprojeto;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.db.DefaultDataHelper;

public class SplashScreen extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DefaultDataHelper db = new DefaultDataHelper(this);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                mostrarMainActivity();
            }
        }, 1000);
    }

    private void mostrarMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.finish();
    }
}
