package com.example.myapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomAdapter;
import com.example.myapplication.bean.Custom;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    EditText name_input_search;
    Button search_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        name_input_search = findViewById(R.id.name_input_search);
        search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(SearchActivity.this);
                if (name_input_search.getText().toString().isEmpty()){
                    Toast.makeText(SearchActivity.this, "MISSING required information", Toast.LENGTH_SHORT).show();

                }
                else{
                    String userName = name_input_search.getText().toString().trim();
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("username",userName);
                    startActivity(intent);
                }
            }
        });

    }

}