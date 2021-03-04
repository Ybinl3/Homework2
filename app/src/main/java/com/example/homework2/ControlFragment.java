package com.example.homework2;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ControlFragment extends Fragment implements View.OnClickListener{

    private static final String LOG_NAME= MainActivity.class.getSimpleName();;
    TextView timeText;
    TextView lapText;
    Timer timer;
    boolean running;
    private TimerAsyncTask asyncTask;
    Button startB;
    Button lapB;
    Button resetB;
    Button listB;

    private OnFragmentInteractionListener cListener;

    public ControlFragment(){

    }
    private class TimerAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            String currTime = String.format("%02d:%02d:%02d", values[0], values[1], values[2]);
            timeText.setText(currTime);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            while(running){
                timer.calcTimer();
                publishProgress(timer.getHour(), timer.getMin(), timer.getSec());
                try{
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
            return null;
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        ColorDrawable sbBackground = (ColorDrawable) startB.getBackground();
        outState.putString("btext",startB.getText().toString());
        outState.putInt("color", sbBackground.getColor());
        outState.putString("time", timeText.getText().toString());
        outState.putStringArrayList("laplist", timer.getLapTimes());
        if(asyncTask != null && asyncTask.getStatus() == AsyncTask.Status.RUNNING){
            outState.putBoolean("running", true);
        }
        else{
            outState.putBoolean("running", false);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        if(asyncTask != null && asyncTask.getStatus() == AsyncTask.Status.RUNNING){
            asyncTask.cancel(true);
            asyncTask = null;
        }
        super.onDestroy();
        Toast.makeText(getActivity(), "onDestroy finished", Toast.LENGTH_SHORT).show();
        Log.i(LOG_NAME, "onDestroy finished");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        ColorDrawable sbBackground = (ColorDrawable) startB.getBackground();
        if (savedInstanceState != null){
            startB.setBackgroundColor(savedInstanceState.getInt("color"));
            startB.setText(savedInstanceState.getString("btext"));
            timeText.setText(savedInstanceState.getString("time"));
            running = savedInstanceState.getBoolean("running");
        }
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            startB.setBackgroundColor(savedInstanceState.getInt("color"));
            startB.setText(savedInstanceState.getString("btext"));
            timeText.setText(savedInstanceState.getString("time"));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_control, container, false);
        startB = view.findViewById(R.id.startBtn);
        lapB = view.findViewById(R.id.lapBtn);
        resetB = view.findViewById(R.id.resetBtn);
        listB = view.findViewById(R.id.listBtn);
        timer = new Timer();
        running = false;
        asyncTask = new TimerAsyncTask();
        timeText = view.findViewById(R.id.timetextView);

        startB.setOnClickListener(this);
        lapB.setOnClickListener(this);
        resetB.setOnClickListener(this);
        listB.setOnClickListener(this);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            listB.setVisibility(View.INVISIBLE);
        }
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener){
            this.cListener = (OnFragmentInteractionListener) context;
        }
        else{
            throw new RuntimeException("Implement OnFragmentInteractionListener");
        }
    }
    public void onDetach(){
        super.onDetach();
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.startBtn){
            if(startB.getText().equals("Start")){
                //cListener.onButtonClicked(0);
                startB.setText("Stop");
                startB.setBackgroundColor(Color.RED);
                running = true;
                if(asyncTask.getStatus() != AsyncTask.Status.RUNNING){
                    asyncTask = new TimerAsyncTask();
                    asyncTask.execute();
                }


            }
            else{
                startB.setText("Start");
                startB.setBackgroundColor(Color.parseColor("#4CAF51"));
                running = false;

            }
        }
        else if(view.getId() == R.id.resetBtn){
            timer.resetTimer();
            String resetTime = String.format("%02d:%02d:%02d", timer.getHour(), timer.getMin(), timer.getSec());
            timeText.setText(resetTime);
        }
        else if(view.getId() == R.id.lapBtn){
            timer.getLapTimes().add(timeText.getText().toString());
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE){
                cListener.onButtonClicked(0);
            }
        }
        else if(view.getId() == R.id.listBtn){
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT){
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("laplist", timer.getLapTimes());
                startActivity(intent);
            }

        }
        else{
            cListener.onButtonClicked(1);
        }

    }


    public interface OnFragmentInteractionListener{
        void onButtonClicked(int id);
    }
}
