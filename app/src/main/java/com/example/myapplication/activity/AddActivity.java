package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

// This class create new customer information page, and insert the value into customer database
public class AddActivity extends AppCompatActivity {

    EditText name_input, age_input, birthday_input, gender_input, companyName_input,
            country_input, cusPhone_input, email_input, language_input, location_input,
            interest_input, clientInfo_input;
    Button add_button;

    //set up customer information page values
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        age_input = findViewById(R.id.age_input);
        birthday_input = findViewById(R.id.birthday_input);
        gender_input = findViewById(R.id.gender_input);
        companyName_input = findViewById(R.id.companyName_input);
        country_input = findViewById(R.id.country_input);
        cusPhone_input = findViewById(R.id.cusPhone_input);
        email_input = findViewById(R.id.email_input);
        language_input = findViewById(R.id.language_input);
        location_input = findViewById(R.id.location_input);
        interest_input = findViewById(R.id.interest_input);
        clientInfo_input = findViewById(R.id.clientInfo_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {

            // when we hit the + button, will create a database of customer (one row of the customer table)
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(AddActivity.this);
                if (name_input.getText().toString().isEmpty() ||
                        age_input.getText().toString().isEmpty() ||
                        birthday_input.getText().toString().isEmpty() ||
                        gender_input.getText().toString().isEmpty()) {
                    Toast.makeText(AddActivity.this, "MISSING required information", Toast.LENGTH_SHORT).show();
                } else {

                    String userName = name_input.getText().toString().trim();
                    String age = age_input.getText().toString().trim();

                    // determine if ages is not entered correctly

                    if (Integer.valueOf(age) > 100) {
                        age = "100";
                    } else if (Integer.valueOf(age) < 0) {
                        age = "0";
                    }

                    String birthday = birthday_input.getText().toString().trim();
                    String gender = gender_input.getText().toString().trim();

//                    for (int i = 0; i < gender.length(); i++){
//                        char ch = gender.charAt(i);
//                        if ( ch >= '0' && ch <= '9'){
//                            Toast.makeText(AddActivity.this, "Wrong Gender Information", Toast.LENGTH_SHORT).show();
//                            break;
//                        }
//                    }

                    String company = companyName_input.getText().toString().trim();
                    String country = country_input.getText().toString().trim();
                    String phone = cusPhone_input.getText().toString().trim();
                    String email = email_input.getText().toString().trim();
                    String language = language_input.getText().toString().trim();
                    String location = location_input.getText().toString().trim();
                    String interest = interest_input.getText().toString().trim();
                    String information = clientInfo_input.getText().toString().trim();

                    myDB.add_customer(userName, age, birthday, gender, company, country, phone,
                            email, language, location, interest, information);
                }
                Intent intent = new Intent(AddActivity.this, CreateDBActivity.class);
                startActivity(intent);
            }
        });
    }
}