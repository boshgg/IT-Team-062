package com.example.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.db.DBHelper;

public class UserService {
    private DBHelper dbHelper;
    public UserService(Context context){
        dbHelper = new DBHelper(context);
    }

    // check username and password while login
    public boolean login(String name, String password){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userData where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[] {name, password});
        if (cursor.getCount() > 0) {
            return true;

        }
        return false;
    }


}
