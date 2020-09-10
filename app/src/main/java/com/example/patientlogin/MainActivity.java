package com.example.patientlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patientlogin.ui.database;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login_btn;
    private Button reg_btn;
    private EditText username;
    private EditText password;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.login_btn);
        reg_btn = findViewById(R.id.reg_btn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn.setOnClickListener(this);
        reg_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regIntent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        String name = username.getText().toString().trim();
        String pw = password.getText().toString().trim();
        Intent changeView = null;
        // access to database
        db = new database(getApplicationContext());
        // test in database
        if(db.checkPair(name, pw)) {
            changeView = new Intent(getApplicationContext(), afterLoginActivity.class);
            startActivity(changeView);
        } else {
            Toast msg = Toast.makeText(getApplicationContext(), "Login failed, " +
                    "Password or username is incorrect", Toast.LENGTH_SHORT);
            msg.show();
        }

    }
}