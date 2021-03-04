package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Activity;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment implements View.OnClickListener{
    RecyclerView listV;
    Button controlB;
    private OnFragmentInteractionListener lListener;


    public ListFragment(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        listV = (RecyclerView) view.findViewById(R.id.lapList);
        controlB = (Button) view.findViewById(R.id.ctrlBtn);
        controlB.setOnClickListener(this);

        return view;
    }
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener){
            this.lListener = (OnFragmentInteractionListener) context;
        }
        else{
            throw new RuntimeException("Implement OnFragmentInteractionListener");
        }
    }
    public void onDetach(){

        super.onDetach();
    }
    public void onClick(View view){
        if(view.getId() == R.id.ctrlBtn){

            lListener.onButtonClicked(0);

        }
    }
    public interface OnFragmentInteractionListener{
        void onButtonClicked(int id);
    }
}
