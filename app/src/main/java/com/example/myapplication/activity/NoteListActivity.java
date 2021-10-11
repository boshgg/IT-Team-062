package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NoteListAdapter;
import com.example.myapplication.bean.Custom;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<Custom> allData;
    NoteListAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_note_list);

        recyclerView = findViewById(R.id.recyclerView);
        allData = new ArrayList<>();
        customAdapter = new NoteListAdapter(this,allData);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getDbData();
    }

    // check all the customer in database
    private void getDbData() {
        CustomerInfoDBHelper helper = new CustomerInfoDBHelper(this);
        Cursor cursor = helper.readALLData();

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