package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class CustomerInfoDBHelper extends SQLiteOpenHelper {
    private Context mContext;

    private static final String DATABASE_NAME = "Customer.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_customers";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_AGE = "age";


    CustomerInfoDBHelper(@Nullable Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
        mContext=context;
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_BIRTHDAY + " TEXT, "+
                        COLUMN_AGE + " INTEGER);";
        sqLiteDatabase.execSQL(query);

    }

    public void add_customer(String name, int age, String birthday){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_BIRTHDAY,birthday);
        cv.put(COLUMN_AGE, age);
        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(mContext,"FAILED", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readALLData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String birthday, String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_BIRTHDAY, birthday);
        cv.put(COLUMN_AGE, age);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});

        if(result == -1 ){
            Toast.makeText(mContext, "Failed to update", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Updated successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
