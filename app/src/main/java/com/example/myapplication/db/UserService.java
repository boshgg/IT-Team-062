package com.example.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.db.DBHelper;

public class UserService {
    private MyDatabaseHelper dbHelper;
    public UserService(Context context){
        dbHelper = new MyDatabaseHelper(context);

    }

    // check username and password while login
    public boolean login(String name, String password){
        dbHelper.openDatabase();
        String result = dbHelper.QuerySQL(name, password);
        if (result.equals("success")){
            return true;
        }
        return false;
    }


}
