package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    EditText name_input, age_input, birthday_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        age_input = findViewById(R.id.age_input);
        birthday_input = findViewById(R.id.birthday_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(AddActivity.this);
                myDB.add_customer(name_input.getText().toString().trim(),
                        Integer.valueOf(age_input.getText().toString().trim()),
                        birthday_input.getText().toString().trim());
                Intent intent = new Intent(AddActivity.this, CreateDBActivity.class);
                startActivity(intent);
            }
        });


    }
}