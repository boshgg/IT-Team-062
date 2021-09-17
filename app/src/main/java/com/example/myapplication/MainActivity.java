package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private EditText mEtUserName;
    private EditText mEtPassword;
    private String userName;
    private String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnLogin = findViewById((R.id.btn_login));
        mBtnRegister = findViewById((R.id.btn_register));
        mEtUserName = findViewById(R.id.userName);
        mEtPassword = findViewById(R.id.password);
        mBtnLogin.setOnClickListener( v -> {
            userName = mEtUserName.getText().toString().trim();
            userPassword = mEtPassword.getText().toString().trim();
            if (checkLogin(userName,userPassword)) {
                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "login Failed", Toast.LENGTH_SHORT).show();
            }
        });

        mEtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("EditText", charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        setListeners();

    }

    //check if the account is valid
    private Boolean checkLogin(String userName, String userPassword){
        String loginInfo = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userData where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[] {userName, userPassword});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    /***
     * set listeners for all the buttons in this activity
     */
    private void setListeners(){
        OnClick onClick = new OnClick();

        mBtnLogin.setOnClickListener(onClick);
        mBtnRegister.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.btn_login:
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                    break;
                case R.id.btn_register:
                    intent = new Intent(MainActivity.this, RegisterActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}