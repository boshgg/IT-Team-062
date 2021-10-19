package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomAdapter;
import com.example.myapplication.bean.Custom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    CustomerInfoDBHelper myDB;
    ArrayList<String> customer_id, customer_name, customer_birthday, customer_age, customer_gender,
            customer_company, customer_country, customer_phone, customer_email, customer_language,
            customer_location, customer_interest, customer_information;
    CustomAdapter customAdapter;

    private List<Custom> allData;

    private String name_got;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        recyclerView = findViewById(R.id.recyclerView3);
        empty_imageview = findViewById(R.id.empty_imageView3);
        no_data = findViewById(R.id.no_data3);
        Intent intent = getIntent();
        name_got = intent.getStringExtra("username");


        myDB = new CustomerInfoDBHelper(SearchResultActivity.this);
        customer_id = new ArrayList<>();
        customer_name = new ArrayList<>();
        customer_birthday = new ArrayList<>();
        customer_age = new ArrayList<>();
        customer_gender = new ArrayList<>();
        customer_company = new ArrayList<>();
        customer_country = new ArrayList<>();
        customer_phone = new ArrayList<>();
        customer_email = new ArrayList<>();
        customer_language = new ArrayList<>();
        customer_location = new ArrayList<>();
        customer_interest = new ArrayList<>();
        customer_information = new ArrayList<>();

        storeDataInArrays();

        allData = new ArrayList<>();
        customAdapter = new CustomAdapter(this, allData);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));

        getDbData();




    }
    void storeDataInArrays(){
        Cursor cursor = myDB.selectRequiredData(name_got);
        if (cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);

        }
        else{
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
    private void getDbData() {
        CustomerInfoDBHelper helper = new CustomerInfoDBHelper(this);
        Cursor cursor = helper.selectRequiredData(name_got);

        try {
            while (cursor.moveToNext()){

                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String age = cursor.getString(cursor.getColumnIndexOrThrow("age"));
                String birth = cursor.getString(cursor.getColumnIndexOrThrow("birthday"));
                String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
                String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
                String country = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
//                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String language = cursor.getString(cursor.getColumnIndexOrThrow("language"));
                String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
                String interest = cursor.getString(cursor.getColumnIndexOrThrow("interest"));
                String information = cursor.getString(cursor.getColumnIndexOrThrow("information"));

                Custom custom = new Custom();
                custom.setId(id);
                custom.setName(name);
                custom.setAge(age);
                custom.setBirthday(birth);
                custom.setGender(gender);
                custom.setCompany(company);
                custom.setEmail(email);
                custom.setLanguage(language);
                custom.setLocation(location);
                custom.setInterest(interest);
                custom.setInformation(information);

                allData.add(custom);
            }
            customAdapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}