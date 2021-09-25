package com.example.myapplication.db;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.example.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "it_db.db";
    public static final String PACKAGE_NAME = "com.example.myapplication";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME + "/databases";
    private SQLiteDatabase database;
    private Context context;
    static int dbVersion = 1;
    static String name = "it_db.db";

    MyDatabaseHelper(Context context){
        super(context,name,null,dbVersion);
        this.context = context;
    }

    // open database and check path
    public void openDatabase() {
        File dFile=new File(DB_PATH);
        if (!dFile.exists()) {
            dFile.mkdir();
        }
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    // import the default database file
    private SQLiteDatabase openDatabase(String dbfile) {
        try {
            if (!(new File(dbfile).exists())) {
                InputStream is = this.context.getResources().openRawResource(
                        R.raw.it_db);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                    null);
            return db;
        }catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }

    // query username and password
    // given result
    // success for exist username and password
    // empty for doesn't exist
    // error for SQLexceptions
    public String QuerySQL(String str1,String str2) {

        String result = "";
        try{
            Cursor cursor = database.rawQuery("select * from userData where name=? and password=? ", new String[]{str2,str1 });
            if (cursor.getCount() > 0)
            {
                result= "success" ;
            }
            cursor.close();
            database.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            result += "error" + e.getMessage();
        }
        return result;
    }
    public void closeDatabase()
    {
        this.database.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
