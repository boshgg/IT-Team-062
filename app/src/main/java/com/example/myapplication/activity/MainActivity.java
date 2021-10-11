package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.UserService;

public class MainActivity extends AppCompatActivity {
    final UserService userService  = new UserService(this);
    private Button mBtnLogin;
    private Button mBtnRegister;
    private EditText mEtUserName;
    private EditText mEtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnLogin = findViewById((R.id.btn_login));
        mBtnRegister = findViewById((R.id.btn_register));
        mEtUserName = findViewById(R.id.userName);
        mEtPassword = findViewById(R.id.password);
        setListeners();

    }


    /***
     * set listeners for all the buttons in this activity
     */
    private void setListeners(){
        OnClick onClick = new OnClick();
        mBtnRegister.setOnClickListener(onClick);
        mBtnLogin.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = null;
            if (view.getId() == R.id.btn_register){
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }else if (view.getId() == R.id.btn_login){
                String username =mEtUserName.getText().toString().trim();
                String password =mEtPassword.getText().toString().trim();
                if (username.isEmpty()){
                    Toast.makeText(MainActivity.this, "username is empty", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(MainActivity.this, "password is empty", Toast.LENGTH_SHORT).show();
                }
                // activity jumped after login
                // waiting for changing activity
                else if (userService.login(username,password)) {
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    intent = new Intent(MainActivity.this, CreateDBActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "login Failed", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}