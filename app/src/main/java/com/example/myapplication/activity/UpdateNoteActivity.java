package com.example.myapplication.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.Calendar;

public class UpdateNoteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText cid_input, event_input, mEtDate, type_input, note_input;
    Button update_button, delete_button,birthday_button;
    int nid;
    String cid, event, date, note, type;

    //get all the changing value in the layout(activity_update)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_update);
        birthday_button = findViewById(R.id.birthday_choose);
        birthday_button.setOnClickListener(this);
        cid_input = findViewById(R.id.cid_input2);
        event_input = findViewById(R.id.event_input2);
        mEtDate = findViewById(R.id.et_date);
        note_input = findViewById(R.id.note_input2);
        type_input = findViewById(R.id.type_input2);
        update_button = findViewById(R.id.add_button2);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(cid);
        }
        // set up the update button, when mouse click the button will update all the values into customer database
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(UpdateNoteActivity.this);

                String name = cid_input.getText().toString();
                String event = event_input.getText().toString();
                String date = mEtDate.getText().toString();
                String note = note_input.getText().toString();
                String type = type_input.getText().toString();

                myDB.updateNoteData(nid+"", name, event, date, note, type);

                Intent intent = new Intent(UpdateNoteActivity.this, CreateDBActivity.class);
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
    //get the values that need to show on the list
    void getAndSetIntentData() {

        try {
            nid = getIntent().getIntExtra("nid",-1);
            cid = getIntent().getStringExtra("cid");
            event = getIntent().getStringExtra("event");
            date = getIntent().getStringExtra("date");
            type = getIntent().getStringExtra("type");
            note = getIntent().getStringExtra("note");

            cid_input.setText(cid);
            event_input.setText(event);
            mEtDate.setText(date);
            type_input.setText(type);
            note_input.setText(note);
        }catch (Exception e){
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
        }

    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + cid + " ?");
        builder.setMessage("Are you sure to delete " + cid + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(UpdateNoteActivity.this);
                myDB.deleteOneRow2(nid+"");
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


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.birthday_choose:

                Calendar calendar=Calendar.getInstance();
                DatePickerDialog pickerDialog = new DatePickerDialog(UpdateNoteActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String desc=String.format("%d-%d-%d",year, month+1, dayOfMonth);
                                mEtDate.setText(desc);


                            }
                        }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
                break;
        }
    }
}