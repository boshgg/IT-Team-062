package com.example.myapplication.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

//This class create the customer table in the database
public class CustomerInfoDBHelper extends SQLiteOpenHelper {

    private Context mContext;
    private Context nContext;

    private static final String DATABASE_NAME = "Customer.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME1 = "my_customers";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_COMPANY = "company";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_LANGUAGE = "language";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_INTEREST = "interest";
    private static final String COLUMN_INFORMATION = "information";

    private static final String TABLE_NAME2 = "customers_note";
    private static final String COLUMN_NID = "nid";
    private static final String COLUMN_EVENT = "event";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_CUSTOMNAME = "name";
    private static final String COLUMN_USERID = "userId";
    CustomerInfoDBHelper(@Nullable Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
        mContext=context;
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1 + TABLE_NAME2 );
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "CREATE TABLE " + TABLE_NAME1 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER, "+
                COLUMN_BIRTHDAY + " TEXT, "+
                COLUMN_GENDER + " TEXT, " +
                COLUMN_COMPANY + " TEXT, " +
                COLUMN_COUNTRY + " TEXT, " +
//                        COLUMN_PHONE + " INTERGER, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_LANGUAGE + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_INTEREST + " TEXT, " +
                COLUMN_INFORMATION + " TEXT );";
        sqLiteDatabase.execSQL(query1);

        String query2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_NID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMNAME + " TEXT, "+
                COLUMN_EVENT + " TEXT, " +
                COLUMN_DATE + " INTEGER, "+
                COLUMN_TYPE + " TEXT, "+
                COLUMN_NOTE + " TEXT, "+
                COLUMN_USERID + " TEXT );";
        sqLiteDatabase.execSQL(query2);

    }

    // this function is put all the values AddActivity class into the database
    public void add_customer(String name, String age,String birthday, String gender,
                             String company, String country, String phone, String email, String language,
                             String location, String interest, String information){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_BIRTHDAY,birthday);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_COUNTRY, country);
//        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_LANGUAGE, language);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_INTEREST, interest);
        cv.put(COLUMN_INFORMATION, information);

        long result = db.insert(TABLE_NAME1,null,cv);
        //check the create is not success
        if (result <0){
            Toast.makeText(mContext,"FAILED", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }

    }

    public void add_note(String cid, String event, String date, String type, String note,int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(COLUMN_CUSTOMNAME, cid);
        cv.put(COLUMN_EVENT, event);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TYPE,type);
        cv.put(COLUMN_NOTE, note);
        cv.put(COLUMN_USERID, userId+"");

        long result = db.insert(TABLE_NAME2,null,cv);
        if (result <0){
            Toast.makeText(mContext,"FAILED", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }
    }

    // get the whole customer table
    Cursor readALLData(){
        String query = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readALLNoteData(int userId){
        String query = "SELECT * FROM " + TABLE_NAME2+" where userId = "+userId ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor selectRequiredData(String name){
        String query = "SELECT * FROM " + TABLE_NAME1 + " WHERE " + COLUMN_NAME + " GLOB " + "'" + "*" + name + "*" + "'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    //this function get all the values from the updateActivity, and change the values in the table
    void updateData(String row_id, String name, String birthday, String age, String gender,
                    String company, String country, String phone, String email, String language,
                    String location, String interest, String information){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_BIRTHDAY, birthday);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_COUNTRY, country);
//        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_LANGUAGE, language);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_INTEREST, interest);
        cv.put(COLUMN_INFORMATION, information);

        long result = db.update(TABLE_NAME1, cv, "id=?", new String[]{row_id});
        //check the update does not success
        if(result < 1 ){
            Toast.makeText(mContext, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    //this function get all the values from the updateActivity, and change the values in the table
    void updateNoteData(String nid, String name, String event, String date, String note, String type){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMNAME, name);
        cv.put(COLUMN_EVENT, event);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TYPE,type);
        cv.put(COLUMN_NOTE, note);

        long result = db.update(TABLE_NAME2, cv, "nid=?", new String[]{nid});
        //check the update does not success
        if(result < 1 ){
            Toast.makeText(mContext, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    //function delete on row of customer table
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME1,"id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(mContext, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow2(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2,"nid=?", new String[]{row_id});
        if (result<=0){
            Toast.makeText(mContext, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    //functions delete whole table
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1);
    }

}

