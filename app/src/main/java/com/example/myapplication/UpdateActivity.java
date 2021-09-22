package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText name_input, birthday_input, age_input;
    Button update_button;
    String id, name, birthday, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        age_input = findViewById(R.id.age_input2);
        birthday_input = findViewById(R.id.birthday_input2);
        update_button = findViewById(R.id.add_button2);
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(UpdateActivity.this);

                name = name_input.getText().toString();
                birthday = birthday_input.getText().toString();
                age = age_input.getText().toString();

                myDB.updateData(id, name, birthday, age);

                Intent intent = new Intent(UpdateActivity.this, CreateDBActivity.class);
                startActivity(intent);
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("birthday") && getIntent().hasExtra("age")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            birthday = getIntent().getStringExtra("birthday");
            age = getIntent().getStringExtra("age");

            name_input.setText(name);
            birthday_input.setText(birthday);
            age_input.setText(age);

        }
        else {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }
}