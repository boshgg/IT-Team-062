package com.example.myapplication.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;

public class UpdateActivity extends AppCompatActivity {
    EditText name_input, birthday_input, age_input, gender_input, companyName_input,
            country_input, cusPhone_input, email_input, language_input, location_input,
            interest_input, clientInfo_input;
    Button update_button, delete_button;
    int id;
    String  name, birthday, age, gender, company, country, phone, email, language, location,
            interest, information;

    //get all the changing value in the layout(activity_update)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        RadioGroup rgButton = findViewById(R.id.rg_selector);
        rgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.male){
                    gender_input.setText("male");
                }else{
                    gender_input.setText("female");
                }
            }
        });
        name_input = findViewById(R.id.name_input2);
        age_input = findViewById(R.id.age_input2);
        birthday_input = findViewById(R.id.birthday_input2);
        gender_input = findViewById(R.id.gender_input2);
        companyName_input = findViewById(R.id.companyName_input2);
        country_input = findViewById(R.id.country_input2);
        cusPhone_input = findViewById(R.id.cusPhone_input2);
        email_input = findViewById(R.id.email_input2);
        language_input = findViewById(R.id.language_input2);
        location_input = findViewById(R.id.location_input2);
        interest_input = findViewById(R.id.interest_input2);
        clientInfo_input = findViewById(R.id.clientInfo_input2);
        update_button = findViewById(R.id.add_button2);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
        }
        // set up the update button, when mouse click the button will update all the values into customer database
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(UpdateActivity.this);

                name = name_input.getText().toString();
                birthday = birthday_input.getText().toString();
                age = age_input.getText().toString();
                gender = gender_input.getText().toString();
                company = companyName_input.getText().toString();
                country = country_input.getText().toString();
                phone = cusPhone_input.getText().toString();
                email = email_input.getText().toString();
                language = language_input.getText().toString();
                location = location_input.getText().toString();
                interest = interest_input.getText().toString();
                information = clientInfo_input.getText().toString();

                myDB.updateData(id+"", name, birthday, age, gender, company, country, phone, email,
                        language, location, interest, information);

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

        // this is the button for user jump into list of note page
        View note_button = findViewById(R.id.note_button);
        note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, NoteListActivity.class);
                intent.putExtra("userId",id);
                startActivity(intent);
            }
        });
    }
    //get the values that need to show on the list
    void getAndSetIntentData() {


        try {
            id = getIntent().getIntExtra("id",-1);
            name = getIntent().getStringExtra("name");
            birthday = getIntent().getStringExtra("birthday");
            age = getIntent().getStringExtra("age");
            gender = getIntent().getStringExtra("gender");

            name_input.setText(name);
            age_input.setText(age);
            birthday_input.setText(birthday);
            gender_input.setText(gender);
        }catch (Exception e){
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
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
                myDB.deleteOneRow(id+"");
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