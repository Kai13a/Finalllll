package com.example.finalllll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

Button Faculty,Employee,Admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Faculty = findViewById(R.id.Faculty_Role);
        Faculty.setOnClickListener(this);

        Employee = findViewById(R.id.Employee_Role);
        Employee.setOnClickListener(this);

        Admin = findViewById(R.id.Admin_Role);
        Admin.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.Employee_Role:
                startActivity(new Intent(this,Employee_Profile.class));
                break;

            case R.id.Admin_Role:
                startActivity(new Intent(this,Admin_Profile.class));
        }
    }
}