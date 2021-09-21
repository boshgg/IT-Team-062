package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static int dbVersion = 1;
    static String name = "it_app.db";

    // create new user data in sql
    // id+username+password
    public static final String CREATE_USERDATA="create table userData(" +
            "id integer primary key autoincrement,"
            +"name varchar(20),"
            +"password varchar(20))";

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
        sqLiteDatabase.execSQL("INSERT into information(name,password) VALUES (it2021, it2021);");
    }
}
