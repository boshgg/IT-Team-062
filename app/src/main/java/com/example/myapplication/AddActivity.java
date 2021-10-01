package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    EditText name_input, age_input, birthday_input,gender_input;
    Button add_button;

    // create layout of the add page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // get the input of something you type in
        name_input = findViewById(R.id.name_input);
        age_input = findViewById(R.id.age_input);
        birthday_input = findViewById(R.id.birthday_input);
        gender_input = findViewById(R.id.gender_input);
        add_button = findViewById(R.id.add_button);

        // if click save button, a new customer will be added into database
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(AddActivity.this);
                // this is used to check if something required you do not type in, cannot add it
                if (name_input.getText().toString().isEmpty()||
                        age_input.getText().toString().isEmpty()||
                        birthday_input.getText().toString().isEmpty()||
                        gender_input.getText().toString().isEmpty()){
                    Toast.makeText(AddActivity.this, "MISSING required information", Toast.LENGTH_SHORT).show();
                }
                else {
                    myDB.add_customer(name_input.getText().toString().trim(),
                            Integer.valueOf(age_input.getText().toString().trim()),
                            birthday_input.getText().toString().trim(),
                            gender_input.getText().toString().trim()
                    );
                }
                // after you add, go back to main page
                Intent intent = new Intent(AddActivity.this, CreateDBActivity.class);
                startActivity(intent);
            }
        });


    }
}