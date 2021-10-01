package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateDBActivity extends AppCompatActivity {

    // play recycler view to visualize our customer database
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    CustomerInfoDBHelper myDB;
    ArrayList<String> customer_id, customer_name, customer_birthday, customer_age, customer_gender;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_data);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateDBActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new CustomerInfoDBHelper(CreateDBActivity.this);
        customer_id = new ArrayList<>();
        customer_name = new ArrayList<>();
        customer_birthday = new ArrayList<>();
        customer_age = new ArrayList<>();
        customer_gender = new ArrayList<>();
        storeDataInArrays();

        // show database by using custom adapter
        customAdapter = new CustomAdapter(CreateDBActivity.this,
                CreateDBActivity.this, customer_id,customer_name,
                customer_birthday, customer_age, customer_gender);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateDBActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // This function is used to refresh page after updating event
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        // Store data from database to arrays
        Cursor cursor = myDB.readALLData();
        if (cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);

        }
        else{
            while (cursor.moveToNext()){
                customer_id.add(cursor.getString(0));
                customer_name.add(cursor.getString(1));
                customer_age.add(cursor.getString(2));
                customer_birthday.add(cursor.getString(3));
                customer_gender.add(cursor.getString(4));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
            // send warning information while delete all data
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // warning message
        builder.setTitle("Delete ALL ?");
        builder.setMessage("Are you sure to delete ALL DATA ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CustomerInfoDBHelper myDB = new CustomerInfoDBHelper(CreateDBActivity.this);
                Toast.makeText(CreateDBActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                myDB.deleteAllData();
                recreate();
                Intent intent = new Intent(CreateDBActivity.this, CreateDBActivity.class);
                startActivity(intent);
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