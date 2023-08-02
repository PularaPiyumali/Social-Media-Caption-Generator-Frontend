package com.example.acg_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;


public class loading extends AppCompatActivity  {

    ProgressBar progressBar;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.loading);
        text = findViewById(R.id.loading1);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();

    }


    public void progressAnimation(){
        ProgressBarAnimation anim=new ProgressBarAnimation(this,progressBar,text,0f,100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }
}
