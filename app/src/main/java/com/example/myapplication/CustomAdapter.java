package com.example.myapplication;

import static com.example.myapplication.R.id.customer_company_txt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.bean.Custom;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private List<Custom> mData;

    //set up adapter of customer
    public CustomAdapter(Context context, List<Custom> data) {
        this.context = context;
        mData = data;
    }

    //create adapter of customer
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    //set up all the values into the list
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Custom custom = mData.get(position);

        String name = custom.getName();
        String age = custom.getAge();
        String id = position+1+"";
        String birth = custom.getBirthday();
        String gender = custom.getGender();
        String company = custom.getCompany();
        String country = custom.getCountry();
        String email = custom.getEmail();
//        String phone = custom.getPhone();
        String language = custom.getLanguage();
        String location = custom.getLocation();
        String interest = custom.getInterest();
        String information = custom.getInformation();

        holder.tvName.setText(name);
        holder.tvAge.setText(age);
        holder.tvId.setText(id);
        holder.tvBirth.setText(birth);
        holder.tvGender.setText(gender);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("age",age);
                intent.putExtra("gender",gender);
                intent.putExtra("birth",birth);
                intent.putExtra("company",company);
                intent.putExtra("country",country);
                intent.putExtra("email",email);
//                intent.putExtra("phone",phone);
                intent.putExtra("language",language);
                intent.putExtra("location",location);
                intent.putExtra("interest",interest);
                intent.putExtra("information",information);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    //get all the values from the layout (my_row.xml)
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAge,tvId, tvBirth, tvGender;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.customer_name_txt);
            tvAge = itemView.findViewById(R.id.customer_age_txt);
            tvId = itemView.findViewById(R.id.customer_id_txt);
            tvBirth = itemView.findViewById(R.id.customer_birthday_txt);
            tvGender = itemView.findViewById(R.id.customer_gender_txt);
        }
    }
}
