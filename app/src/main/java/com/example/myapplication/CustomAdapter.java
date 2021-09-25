package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList customer_id, customer_name, customer_birthday, customer_age, company_name, country_name,
            customer_job, language, customer_location, interest, customer_info;

    CustomAdapter(Activity activity, Context context, ArrayList<String> customerId, ArrayList customer_id, ArrayList customer_name,
                  ArrayList customer_birthday, ArrayList customer_age, ArrayList company_name, ArrayList country_name, ArrayList customer_job,
                  ArrayList language, ArrayList customer_location, ArrayList customer_info){
        this.activity = activity;
        this.context = context;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_birthday = customer_birthday;
        this.customer_age = customer_age;
        this.company_name = company_name;
        this.country_name = country_name;
        this.customer_job = customer_job;
        this.language = language;
        this.customer_location = customer_location;
        this.customer_info = customer_info;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.customer_id_txt.setText(String.valueOf(customer_id.get(position)));
        holder.customer_name_txt.setText(String.valueOf(customer_name.get(position)));
        holder.customer_birthday_txt.setText(String.valueOf(customer_birthday.get(position)));
        holder.customer_age_txt.setText(String.valueOf(customer_age.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = holder.getAdapterPosition();
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(customer_id.get(num)));
                intent.putExtra("name", String.valueOf(customer_name.get(num)));
                intent.putExtra("birthday", String.valueOf(customer_birthday.get(num)));
                intent.putExtra("age", String.valueOf(customer_age.get(num)));
                activity.startActivityForResult(intent,1);

            }
        });



    }

    @Override
    public int getItemCount() {
        return customer_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView customer_id_txt, customer_name_txt,customer_birthday_txt, customer_age_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            customer_id_txt = itemView.findViewById(R.id.customer_id_txt);
            customer_name_txt = itemView.findViewById(R.id.customer_name_txt);
            customer_birthday_txt = itemView.findViewById(R.id.customer_birthday_txt);
            customer_age_txt = itemView.findViewById(R.id.customer_age_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
