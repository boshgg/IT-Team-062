package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList customer_id, customer_name, customer_birthday, customer_age;

    CustomAdapter(Context context, ArrayList customer_id, ArrayList customer_name,
                  ArrayList customer_birthday, ArrayList customer_age){
        this.context = context;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_birthday = customer_birthday;
        this.customer_age = customer_age;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.customer_id_txt.setText(String.valueOf(customer_id.get(position)));
        holder.customer_name_txt.setText(String.valueOf(customer_name.get(position)));
        holder.customer_birthday_txt.setText(String.valueOf(customer_birthday.get(position)));
        holder.customer_age_txt.setText(String.valueOf(customer_age.get(position)));



    }

    @Override
    public int getItemCount() {
        return customer_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView customer_id_txt, customer_name_txt,customer_birthday_txt, customer_age_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            customer_id_txt = itemView.findViewById(R.id.customer_id_txt);
            customer_name_txt = itemView.findViewById(R.id.customer_name_txt);
            customer_birthday_txt = itemView.findViewById(R.id.customer_birthday_txt);
            customer_age_txt = itemView.findViewById(R.id.customer_age_txt);

        }
    }
}
