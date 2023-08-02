package com.example.acg_application;

import android.content.Intent;
import android.view.animation.Animation;
import android.content.Context;
import android.view.animation.Transformation;
import android.widget.TextView;
import android.widget.ProgressBar;

public class ProgressBarAnimation extends Animation {

    private Context con;
    private ProgressBar progressionBar;
    private TextView text;
    private float from;
    private float to;

    public ProgressBarAnimation (Context con, ProgressBar pro, TextView text, float from, float to){
        this.con = con;
        this.progressionBar = pro;
        this.text = text;
        this.from = from;
        this.to= to;

    }

    public ProgressBarAnimation (){

    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t){
        super.applyTransformation(interpolatedTime, t);
        float value = from +(to - from) *interpolatedTime;
        progressionBar.setProgress((int)value);
        text.setText((int)value+" %");

        if(value == to){
            con.startActivity(new Intent(con, caption.class));
        }

    }


}
