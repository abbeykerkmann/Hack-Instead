package com.example.hackinstead;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RidesRVAdapter extends RecyclerView.Adapter<RidesRVAdapter.ViewHolder> {
    private List<String> ridesList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public RidesRVAdapter(Context context, List<String> ridesList) {
        this.mInflater = LayoutInflater.from(context);
        this.ridesList = ridesList;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView rideName;

        ViewHolder(View itemView) {
            super(itemView);
            rideName = itemView.findViewById(R.id.ride_name_history);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getItem(int id) {
        return ridesList.get(id);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.history_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String ride = ridesList.get(position);
        holder.rideName.setText(ride);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return ridesList.size();
    }
}
