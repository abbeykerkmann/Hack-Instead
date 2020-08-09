package com.example.hackinstead;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RidesRVAdapter extends RecyclerView.Adapter<RidesRVAdapter.CustomViewHolder> {
    private List<String> ridesList;
    private AdapterView.OnItemClickListener editListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView ride_name;

        public CustomViewHolder(View view, final OnItemClickListener listener){
            super(view);
            ride_name = (TextView) view.findViewById(R.id.ride_name_history);
        }
    }
    public RidesRVAdapter(List<String> ride) {this.ridesList = ride; }
    @Override
    public RidesRVAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_holder, parent, false);
        return new CustomViewHolder(itemView, (OnItemClickListener) this.editListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RidesRVAdapter.CustomViewHolder holder, int position) {
        String ride = ridesList.get(position);
        holder.ride_name.setText(ride.toString());

    }

    @Override
    public int getItemCount() {
        return ridesList.size();
    }
}
