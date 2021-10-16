package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.Calendar;

// This class create new customer information page, and insert the value into customer database
public class AddActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    EditText name_input, age_input, gender_input, companyName_input,
            country_input, cusPhone_input, email_input, language_input, location_input,
            interest_input, clientInfo_input;
    Button add_button, birthday_button;
    TextView birthday_view;

    //set up customer information page values
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        age_input = findViewById(R.id.age_input);
        birthday_view = findViewById(R.id.birthday_view);
        gender_input = findViewById(R.id.gender_input);
        companyName_input = findViewById(R.id.companyName_input);
        country_input = findViewById(R.id.country_input);
        cusPhone_input = findViewById(R.id.cusPhone_input);
        email_input = findViewById(R.id.email_input);
        language_input = findViewById(R.id.language_input);
        location_input = findViewById(R.id.location_input);
        interest_input = findViewById(R.id.interest_input);
        clientInfo_input = findViewById(R.id.clientInfo_input);
        birthday_button = findViewById(R.id.birthday_choose);
        add_button = findViewById(R.id.add_button);

        findViewById(R.id.birthday_choose).setOnClickListener(this);

        add_button.setOnClickListener(new View.OnClickListener() {

            // when we hit the + button, will create a database of customer (one row of the customer table)
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(AddActivity.this);
                if (name_input.getText().toString().isEmpty() ||
                        age_input.getText().toString().isEmpty() ||
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

                    String birthday = birthday_view.getText().toString().trim();
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

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.birthday_choose){
            //获取日历的一个实例，里面包含了当前的年月日
            Calendar calendar=Calendar.getInstance();
            //构建一个日期对话框，该对话框已经集成了日期选择器
            //DatePickerDialog的第二个构造参数指定了日期监听器
            DatePickerDialog pickerDialog = new DatePickerDialog(AddActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String desc=String.format("%d-%d-%d",year, month, dayOfMonth);
                            birthday_view.setText(desc);
                        }
                    }, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            //把日期对话框显示在界面上
            pickerDialog.show();
        }
    }



    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String desc=String.format("%d-%d-%d",i, i1+1, i2);
        birthday_view.setText(desc);
    }
}