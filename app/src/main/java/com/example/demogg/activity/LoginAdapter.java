package com.example.demogg.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demogg.R;
import com.example.demogg.model.GoogleDrive;

import java.util.ArrayList;
import java.util.Calendar;

public class LoginAdapter extends RecyclerView.Adapter<LoginAdapter.DataViewHolder> {

    ArrayList<GoogleDrive> list = new ArrayList<>();

    public LoginAdapter(ArrayList<GoogleDrive> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        holder.txtName.setText(list.get(position).getName());
        holder.txtSize.setText(list.get(position).getSize()+"KB");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(list.get(position).getCreatedTime().getValue());
        holder.txtCreatedTime.setText(list.get(position).getCreatedTime()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtSize,txtCreatedTime;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtSize = itemView.findViewById(R.id.txtSize);
            txtCreatedTime = itemView.findViewById(R.id.txtCreatedTime);
        }
    }
}
