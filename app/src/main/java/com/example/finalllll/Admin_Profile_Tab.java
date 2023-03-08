package com.example.finalllll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_Profile_Tab extends AppCompatActivity {

    ImageView left_arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_tab);
        left_arrow = findViewById(R.id.right);
        left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.right:
                        startActivity(new Intent(Admin_Profile_Tab.this,Admin_Profile.class));
                        break;
                }
            }
        });
    }
}