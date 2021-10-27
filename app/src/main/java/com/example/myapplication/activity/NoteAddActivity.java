package com.example.myapplication.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Calendar;

public class NoteAddActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    EditText cid_input, event_input,  type_input, note_input ;
    Button add_button, date_button;
    TextView date_view;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_note_add);
        getSupportActionBar().setTitle("Add Note");


        userId = getIntent().getIntExtra("userId", -1);
        cid_input = findViewById(R.id.cid_input);
        date_view = findViewById(R.id.date_input);
        event_input = findViewById(R.id.event_input);
        date_button = findViewById(R.id.choose_date);
        type_input = findViewById(R.id.type_input);
        note_input = findViewById(R.id.note_input);
        add_button = findViewById(R.id.add_button);

        findViewById(R.id.choose_date).setOnClickListener(this);

        add_button.setOnClickListener(new View.OnClickListener() {
            // when we hit the + button, will create a database of customer (one row of the customer table)
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(NoteAddActivity.this);
                if (event_input.getText().toString().isEmpty()||date_view.getText().toString().isEmpty()) {
                    Toast.makeText(NoteAddActivity.this, "MISSING required information", Toast.LENGTH_SHORT).show();
                } else {
                    String cid = cid_input.getText().toString().trim();
                    String event = event_input.getText().toString().trim();
                    String date = date_view.getText().toString().trim();
                    String type = type_input.getText().toString().trim();
                    String note = note_input.getText().toString().trim();

                    myDB.add_note(cid,event,date,type,note,userId);
                }
                Intent intent = new Intent(NoteAddActivity.this, CreateDBActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.choose_date){
            Calendar calendar=Calendar.getInstance();
            DatePickerDialog pickerDialog = new DatePickerDialog(NoteAddActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String desc=String.format("%d-%d-%d",year, month+1, dayOfMonth);
                            date_view.setText(desc);
                        }
                    }, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            pickerDialog.show();
        }
    }



    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String desc=String.format("%d-%d-%d",i, i1+1, i2);
        date_view.setText(desc);
    }

}
