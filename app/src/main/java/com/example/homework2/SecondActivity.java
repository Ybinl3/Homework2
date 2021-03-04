package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener, MyRecyclerViewAdapter.ItemClickListener{


    ListFragment listFragment;
    MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ArrayList<String> laptime = new ArrayList<String>();
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        Bundle b = getIntent().getExtras();
        if(b != null){
            laptime = b.getStringArrayList("laplist");
        }
        RecyclerView recyclerView = findViewById(R.id.lapList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, laptime);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onButtonClicked(int id) {
        if(id == 0){
            finish();
        }
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}