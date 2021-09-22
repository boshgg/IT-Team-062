package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateDBActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    CustomerInfoDBHelper myDB;
    ArrayList<String> customer_id, customer_name, customer_birthday, customer_age;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateDBActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new CustomerInfoDBHelper(CreateDBActivity.this);
        customer_id = new ArrayList<>();
        customer_name = new ArrayList<>();
        customer_birthday = new ArrayList<>();
        customer_age = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(CreateDBActivity.this, customer_id,customer_name,
                customer_birthday, customer_age);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateDBActivity.this));

    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readALLData();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                customer_id.add(cursor.getString(0));
                customer_name.add(cursor.getString(1));
                customer_birthday.add(cursor.getString(2));
                customer_age.add(cursor.getString(3));




            }
        }
    }

}