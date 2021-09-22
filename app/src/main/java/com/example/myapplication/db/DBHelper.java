package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static int dbVersion = 1;
    static String name = "DB.db";

    // create new user data in sql
    // id+username+password
    public static final String CREATE_USERDATA="create table " +
            "userData(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name varchar(20), password varchar(20))";
    private Context mContext;

    public DBHelper(Context context){
        super(context,name,null,dbVersion);
        mContext=context;
    }

    // update data
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //onCreate(db);
    }

    // create
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USERDATA);
        ContentValues cv = new ContentValues();
        cv.put("name", "it2021");
        cv.put("password","it2021");
        sqLiteDatabase.insert("userData", null, cv);

    }
}