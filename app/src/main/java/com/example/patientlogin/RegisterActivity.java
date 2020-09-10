package com.example.patientlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patientlogin.ui.database;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back_btn;
    private Button reg_btn;
    private EditText name;
    private EditText pw;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        back_btn = findViewById(R.id.back_btn);
        reg_btn = findViewById(R.id.submit_btn);
        name = findViewById(R.id.setUsername);
        pw = findViewById(R.id.setPassword);
        back_btn.setOnClickListener(this);
        db = new database(this);
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString().trim();
                String password = pw.getText().toString().trim();
                if (!username.equals("") && !password.equals("")) {
                    long result = db.addUser(username, password);
                    if (result > 0) {
                        Toast.makeText(getApplicationContext(), "Registered!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error Code: 500, Register failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid username and password", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}