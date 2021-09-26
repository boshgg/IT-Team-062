//package com.example.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.myapplication.db.CreateDBANActivity;
//
//import java.util.Locale;
//
//public class AddANActivity extends AppCompatActivity {
//
//    EditText note_input;
//    Button add_button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add);
//
//        note_input = findViewById(R.id.note_input);
//        add_button = findViewById(R.id.add_button);
//        add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(AddANActivity.this);
//                myDB.add_note(note_input.getText().toString().trim());
//                Intent intent = new Intent(AddANActivity.this, CreateDBANActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }
//}