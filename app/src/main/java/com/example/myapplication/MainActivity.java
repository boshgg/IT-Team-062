package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private Button mBtnRegister;
    private EditText mEtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnLogin = findViewById((R.id.btn_login));
        mBtnRegister = findViewById((R.id.btn_register));
        mEtUserName = findViewById(R.id.userName);
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