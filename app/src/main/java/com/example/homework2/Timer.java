package com.example.homework2;

import java.util.ArrayList;

public class Timer {

    private ArrayList<String> lapTimes;
    private int sec;
    private int min;
    private int hour;

    public Timer(){
        lapTimes = new ArrayList<String>();
        sec = 0;
        min = 0;
        hour = 0;
    }
    public void calcTimer(){
        sec++;
        if(sec == 60){
            min++;
            sec = 0;
        }
        if(min == 60){
            hour++;
            min = 0;
        }
    }
    public void resetTimer(){
        sec = 0;
        min = 0;
        hour = 0;

        lapTimes = new ArrayList<String>();

    }

    public ArrayList<String> getLapTimes() {
        return lapTimes;
    }

    public int getSec() {
        return sec;
    }

    public int getMin() {
        return min;
    }

    public int getHour() {
        return hour;
    }
}
