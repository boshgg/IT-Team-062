package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class NoteAddActivity extends AppCompatActivity {

    EditText event_input, date_input, type_input, note_input ;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_note_add);
        event_input = findViewById(R.id.event_input);
        date_input = findViewById(R.id.date_input);
        type_input = findViewById(R.id.type_input);
        note_input = findViewById(R.id.note_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {

            // when we hit the + button, will create a database of customer (one row of the customer table)
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(NoteAddActivity.this);
                if (event_input.getText().toString().isEmpty()||date_input.getText().toString().isEmpty()) {
                    Toast.makeText(NoteAddActivity.this, "MISSING required information", Toast.LENGTH_SHORT).show();
                } else {

                    String event = event_input.getText().toString().trim();
                    String date = date_input.getText().toString().trim();
                    String type = type_input.getText().toString().trim();
                    String note = note_input.getText().toString().trim();
                    myDB.add_note(event,date,type,note);
                }
                Intent intent = new Intent(NoteAddActivity.this, CreateDBActivity.class);
                startActivity(intent);
            }
        });

    }

}
