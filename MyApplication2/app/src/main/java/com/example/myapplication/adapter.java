package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    Context context;

    ArrayList<User> list;

    public adapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fullname, phone, email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fullname = itemView.findViewById(R.id.user_name_txt);
            phone = itemView.findViewById(R.id.user_phone_txt);
            email = itemView.findViewById(R.id.user_email_txt);
        }
    }

    @NonNull
    @Override
    public adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.fullname.setText(user.getUsername());
        holder.phone.setText(user.getUserphone());
        holder.email.setText(user.getUseremail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
