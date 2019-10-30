package com.firstapp.hytripplan.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.firstapp.hytripplan.R;
import com.firstapp.hytripplan.login.LoginActivity;


public class SplashActivity extends AppCompatActivity {
    private  int time = 4000;   //4초
    Intent intent;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler();

        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, time);



    }

}

