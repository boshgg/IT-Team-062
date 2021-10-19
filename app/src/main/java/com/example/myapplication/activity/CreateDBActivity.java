package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomAdapter;
import com.example.myapplication.bean.Custom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateDBActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    FloatingActionButton search_button;
    ImageView empty_imageview;
    TextView no_data;
    Calendar now = Calendar.getInstance();
    int noti_id = 1;

    CustomerInfoDBHelper myDB;
    ArrayList<String> customer_id, customer_name, customer_birthday, customer_age, customer_gender,
            customer_company, customer_country, customer_phone, customer_email, customer_language,
            customer_location, customer_interest, customer_information;
    CustomAdapter customAdapter;

    private List<Custom> allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_data);
        add_button = findViewById(R.id.add_button);
        search_button = findViewById(R.id.search_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateDBActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateDBActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        myDB = new CustomerInfoDBHelper(CreateDBActivity.this);
        customer_id = new ArrayList<>();
        customer_name = new ArrayList<>();
        customer_birthday = new ArrayList<>();
        customer_age = new ArrayList<>();
        customer_gender = new ArrayList<>();
        customer_company = new ArrayList<>();
        customer_country = new ArrayList<>();
        customer_phone = new ArrayList<>();
        customer_email = new ArrayList<>();
        customer_language = new ArrayList<>();
        customer_location = new ArrayList<>();
        customer_interest = new ArrayList<>();
        customer_information = new ArrayList<>();

        storeDataInArrays();

        allData = new ArrayList<>();
        customAdapter = new CustomAdapter(this,allData);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateDBActivity.this));

        checkBirthDayAndSendNotification();


    }

    @Override
    protected void onResume() {
        super.onResume();
        getDbData();
    }

    private void checkBirthDayAndSendNotification(){
        CustomerInfoDBHelper helper = new CustomerInfoDBHelper(this);
        Cursor cursor = helper.readALLData();

        int now_month = (now.get(Calendar.MONTH) + 1);
        int now_day = now.get(Calendar.DAY_OF_MONTH);
        Log.d("Day", "month"+String.valueOf(now_month));
        Log.d("Day", "day"+String.valueOf(now_day));

        try {
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String birth = cursor.getString(cursor.getColumnIndexOrThrow("birthday"));

                int birthday_month = 0,birthday_day = 0;
                try {
                    String[] dates = birth.split("-");
                     birthday_month = Integer.parseInt(dates[1]);
                     birthday_day = Integer.parseInt(dates[2]);
                }catch (Exception e){

                }


                if (birthday_month == now_month){
                    Log.d("Day", String.valueOf("birthday:"+birthday_day));
                    int day_close = birthday_day - now_day;
                    Log.d("Day", String.valueOf("day_close:"+day_close));
                    if (day_close <= 3 && day_close >=1){
                        String birthdayNotification = "Customer "+ name +"'s birthday is coming in " + day_close +" days";
                        NotificationManager notificationManager = (NotificationManager) CreateDBActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);

                        Intent intent = new Intent(CreateDBActivity.this, CreateDBActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(CreateDBActivity.this, 0, intent, 0);

                        String channelId = "my_channel";
                        NotificationCompat.Builder notification = new NotificationCompat.Builder(CreateDBActivity.this, channelId)
                                .setContentTitle("Some customers' Birthday is coming")
                                .setContentText(birthdayNotification)
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setWhen(System.currentTimeMillis());


                        notificationManager.notify(noti_id, notification.build());
                        noti_id+=1;
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String createNotificationChannel(String channelID, String channelNAME, int level) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(channelID, channelNAME, level);
            manager.createNotificationChannel(channel);
            return channelID;
        } else {
            return null;
        }
    }

    // check all the customer in database
    private void getDbData() {
        CustomerInfoDBHelper helper = new CustomerInfoDBHelper(this);
        Cursor cursor = helper.readALLData();

        try {
            while (cursor.moveToNext()){

                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String age = cursor.getString(cursor.getColumnIndexOrThrow("age"));
                String birth = cursor.getString(cursor.getColumnIndexOrThrow("birthday"));
                String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
                String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
                String country = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
//                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String language = cursor.getString(cursor.getColumnIndexOrThrow("language"));
                String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
                String interest = cursor.getString(cursor.getColumnIndexOrThrow("interest"));
                String information = cursor.getString(cursor.getColumnIndexOrThrow("information"));

                Custom custom = new Custom();
                custom.setId(id);
                custom.setName(name);
                custom.setAge(age);
                custom.setBirthday(birth);
                custom.setGender(gender);
                custom.setCompany(company);
                custom.setEmail(email);
                custom.setLanguage(language);
                custom.setLocation(location);
                custom.setInterest(interest);
                custom.setInformation(information);

                allData.add(custom);
            }
            customAdapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            recreate();
        }
    }


    void storeDataInArrays(){
        Cursor cursor = myDB.readALLData();
        if (cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);

        }
        else{
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

        }
        if(item.getItemId() == R.id.share_customer){
            shareData();
        }
        if(item.getItemId() == R.id.check_birthday){
            checkBirthDayAndSendNotification();
        }
        return super.onOptionsItemSelected(item);
    }

    void shareData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Share Data");
        builder.setMessage("Do you want to share your customer database?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(CreateDBActivity.this, SendEmailActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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