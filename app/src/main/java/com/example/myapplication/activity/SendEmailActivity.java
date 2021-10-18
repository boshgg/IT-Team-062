package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.regex.Pattern;


public class SendEmailActivity extends AppCompatActivity {
    private Button mBtnSend;
    private EditText mEtEmailAddress;
    private EditText mEtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBtnSend = findViewById((R.id.btn_send));
        mEtEmailAddress = findViewById((R.id.editTextTextEmailAddress));
        mEtName = findViewById((R.id.editTextTextPersonName));
        OnClick onClick = new OnClick();
        mBtnSend.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = null;
            if (view.getId() == R.id.btn_send){
                String emailAddress =mEtEmailAddress.getText().toString().trim();
                String name =mEtName.getText().toString().trim();
                if (isValidEmailId(emailAddress)){
                    String[] address = {emailAddress};
                    sendDBFileWithEmail(address, name);
                    Toast.makeText(SendEmailActivity.this, "Send Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SendEmailActivity.this, "Incorrect Email Address, Please try again", Toast.LENGTH_SHORT).show();
                }
                intent = new Intent(SendEmailActivity.this, CreateDBActivity.class);
                startActivity(intent);
            }
        }
    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private void sendDBFileWithEmail(String[] emailAddress, String sender) {
        String subject = "CRM shared customer database";
        String body = "shared from "+ sender;
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

        String DB_NAME = "Customer.db";
        String PACKAGE_NAME = "com.example.myapplication";
        String DB_PATH = "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/"
                + PACKAGE_NAME + "/databases";

        Uri uri = Uri.parse("file:/" + DB_PATH + "/" + DB_NAME);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        emailIntent.setType("application/vnd.sqlite3");

        startActivity(Intent.createChooser(emailIntent, "send database"));
    }

}