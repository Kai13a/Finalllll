package com.example.finalllll;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener {
    String[] role = {"Employee", "Faculty", "Admin"};
    String[] campus = {"Alangilan", "Balayan", "Lemery", "Lipa", "Lobo", "Mabini", "Malvar", "Nasugbu", "Pablo Borbon", "Rosario", "San Juan"};
    private AutoCompleteTextView autoCompleteTxtRole;
    private AutoCompleteTextView autoCompleteTxtCampus;

    ArrayAdapter<String> adapterItems;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    private ProgressBar progressBar6,progressBar7,progressBar8;
    private int ProgressStatus3 = 0;
    private Handler handler3 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mAuth = FirebaseAuth.getInstance();
        TextView registerUser = findViewById(R.id.registerUser4);
        registerUser.setOnClickListener(this);

        autoCompleteTxtRole = findViewById(R.id.reg_role);
        autoCompleteTxtCampus = findViewById(R.id.reg_campus);
        progressBar = findViewById(R.id.progressBar);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,role);
        autoCompleteTxtRole.setAdapter(adapterItems);

        autoCompleteTxtRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,campus);
        autoCompleteTxtCampus.setAdapter(adapterItems);

        autoCompleteTxtCampus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });



        progressBar = findViewById(R.id.progressBar);

        progressBar6 = findViewById(R.id.Progressbar10);
        progressBar7 = findViewById(R.id.Progressbar11);
        progressBar8 = findViewById(R.id.Progressbar12);


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (ProgressStatus3 < 100){
                    //mProgressStatus += a+b;
                    ProgressStatus3 = 100;
                    android.os.SystemClock.sleep(50);

                    handler3.post(new Runnable() {
                        @Override
                        public void run() {

                            progressBar6.setProgress(ProgressStatus3);
                            progressBar7.setProgress(ProgressStatus3);
                            progressBar8.setProgress(ProgressStatus3);

                        }
                    });
                }
                handler3.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerUser4:
                registerUser4();
        }
    }

    private void registerUser4() {

        String role = autoCompleteTxtRole.getText().toString().trim();
        String campus = autoCompleteTxtCampus.getText().toString().trim();



        progressBar.setVisibility(View.VISIBLE);

        User4 user4 = new User4(role,campus);

        FirebaseDatabase.getInstance().getReference("Users/Infos/PART1")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .setValue(user4).addOnCompleteListener(task1 -> {


                    if(task1.isSuccessful()){

                        Toast.makeText(MainActivity5.this,"Data Inserted", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                            startActivity(new Intent(MainActivity5.this,Employee_Profile.class));

                    }else{
                        Toast.makeText(MainActivity5.this, "Fail to register! Try Again", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}