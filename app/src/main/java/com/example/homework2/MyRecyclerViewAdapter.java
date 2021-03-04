package com.example.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> laps;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public MyRecyclerViewAdapter(Context context, ArrayList<String> laps) {
        this.mInflater = LayoutInflater.from(context);
        this.laps = laps;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        String lap = laps.get(position);
        holder.lapText.setText((position + 1) + ". " + lap);
    }

    @Override
    public int getItemCount() {
        return laps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView lapText;
        public ViewHolder(View itemView) {
            super(itemView);
            lapText = itemView.findViewById(R.id.lapText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null){
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
    public String getItem(int id){
        return laps.get(id);
    }
    public void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
