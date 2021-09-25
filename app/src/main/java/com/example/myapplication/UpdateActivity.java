package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText name_input, birthday_input, age_input, gender_input;
    Button update_button, delete_button;
    String id, name, birthday, age, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        age_input = findViewById(R.id.age_input2);
        birthday_input = findViewById(R.id.birthday_input2);
        gender_input = findViewById(R.id.gender_input2);
        update_button = findViewById(R.id.add_button2);
        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(UpdateActivity.this);

                name = name_input.getText().toString();
                birthday = birthday_input.getText().toString();
                age = age_input.getText().toString();
                gender = gender_input.getText().toString();

                myDB.updateData(id, name, birthday, age, gender);

                Intent intent = new Intent(UpdateActivity.this, CreateDBActivity.class);
                startActivity(intent);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("birthday") && getIntent().hasExtra("age")
                && getIntent().hasExtra("gender")){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            birthday = getIntent().getStringExtra("birthday");
            age = getIntent().getStringExtra("age");
            gender = getIntent().getStringExtra("gender");

            name_input.setText(name);
            age_input.setText(age);
            birthday_input.setText(birthday);
            gender_input.setText(gender);

        }
        else {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}