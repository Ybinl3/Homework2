package com.example.homework2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ControlFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener {

    ControlFragment controlFragment;
    ListFragment listFragment;
    TextView lapText;
    //public static final int startColor = Color.parseColor("#4CAF50");
    //public static final int stopColor = Color.parseColor("FF0000");

    //if timerRun is true which is determined whehter the buttonsays stop or not. THen do asynctask.
    // Maybe put asynctask in Control Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lapText = (TextView) findViewById(R.id.lapView);
        int orientation = getResources().getConfiguration().orientation;
        controlFragment = (ControlFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentC);

        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            controlFragment = (ControlFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentC2);
        }




    }
    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(controlFragment != null){
            getSupportFragmentManager().putFragment(outState, "fragmentControl", controlFragment);
        }

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        getSupportFragmentManager().getFragment(savedInstanceState, "fragmentControl");
        super.onRestoreInstanceState(savedInstanceState);


    }
    */
    @Override
    public void onButtonClicked(int id) {
        if(id == 0){
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < controlFragment.timer.getLapTimes().size(); i++) {
                    s.append(i + " " +controlFragment.timer.getLapTimes().get(i));
                    s.append("\n");
                }
                if (s.length() > 0) {
                    String laps = s.toString();
                    //System.out.println(laps);
                    lapText.setText(s);
                }
            }
        }
    }
}