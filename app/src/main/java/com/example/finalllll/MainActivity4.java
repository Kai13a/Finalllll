package com.example.finalllll;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextBaccalaureate,editTextBA_specific,editTextMasters,editTextMA_specific, editTextDoctorate, editTextPHD_spec,
            editTextPLE,editTextTen_App,editTextRank,editTextTeach_Load,editTextSubj_Taught,editTextAnn_Sal,
            editTextDOA,editTextYrs_Serv;

    String[] pt_ft =  {"Part-Time","Full-Time"};
    private AutoCompleteTextView autoCompleteTxtFTPT;
    ArrayAdapter<String> adapterItems;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    private ProgressBar progressBar4,progressBar5;
    private int ProgressStatus2 = 0;
    private Handler handler2 = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);





        mAuth = FirebaseAuth.getInstance();
        TextView registerUser = findViewById(R.id.registerUser3);
        registerUser.setOnClickListener(this);

        editTextAnn_Sal = findViewById(R.id.ann_sal);
        editTextBA_specific = findViewById(R.id.ba_specific);
        editTextBaccalaureate = findViewById(R.id.baccalaureate);
        editTextDOA = findViewById(R.id.doa);
        editTextMA_specific = findViewById(R.id.ma_spec);
        editTextDoctorate = findViewById(R.id.doctorate);
        editTextMasters = findViewById(R.id.masters);
        editTextPHD_spec = findViewById(R.id.phd_spec);
        editTextPLE = findViewById(R.id.prof_lic_earned);
        editTextTen_App = findViewById(R.id.ten_app);
        editTextRank = findViewById(R.id.rank);
        editTextTeach_Load = findViewById(R.id.teach_load);
        editTextSubj_Taught = findViewById(R.id.subj_taught);
        editTextYrs_Serv = findViewById(R.id.yrs_of_serv);

        autoCompleteTxtFTPT = findViewById(R.id.ft_pt);
        progressBar = findViewById(R.id.progressBar);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,pt_ft);
        autoCompleteTxtFTPT.setAdapter(adapterItems);

        autoCompleteTxtFTPT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        progressBar = findViewById(R.id.progressBar);

        progressBar4 = findViewById(R.id.Progressbar10);
        progressBar5 = findViewById(R.id.Progressbar11);


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (ProgressStatus2 < 100){
                    //mProgressStatus += a+b;
                    ProgressStatus2 = 100;
                    android.os.SystemClock.sleep(50);

                    handler2.post(new Runnable() {
                        @Override
                        public void run() {

                            progressBar4.setProgress(ProgressStatus2);
                            progressBar5.setProgress(ProgressStatus2);
                        }
                    });
                }
                handler2.post(new Runnable() {
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
            case R.id.registerUser3:
                registerUser3();
        }
    }

    private void registerUser3() {

        String ann_sal = editTextAnn_Sal.getText().toString().trim();
        String ba_spec = editTextBA_specific.getText().toString().trim();
        String baccalaureate = editTextBaccalaureate.getText().toString().trim();
        String doa = editTextDOA.getText().toString().trim();
        String ma_spec = editTextMA_specific.getText().toString().trim();
        String doctorate = editTextDoctorate.getText().toString().trim();
        String masters = editTextMasters.getText().toString().trim();
        String phd_spec = editTextPHD_spec.getText().toString().trim();
        String prof_lic_earned = editTextPLE.getText().toString().trim();
        String ten_app = editTextTen_App.getText().toString().trim();
        String rank = editTextRank.getText().toString().trim();
        String teach_load = editTextTeach_Load.getText().toString().trim();
        String subj_taught = editTextSubj_Taught.getText().toString().trim();
        String yrs_of_serv = editTextYrs_Serv.getText().toString().trim();
        String ft_pt = autoCompleteTxtFTPT.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);

        User3 user3 = new User3(ft_pt,baccalaureate,ba_spec,masters,ma_spec,doctorate,phd_spec,
                prof_lic_earned,ten_app,rank,teach_load,subj_taught,ann_sal,doa,yrs_of_serv);

        FirebaseDatabase.getInstance().getReference("Users/PART3")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .setValue(user3).addOnCompleteListener(task1 -> {

                    if(task1.isSuccessful()){
                        //Toast.makeText(Register_User.this,"User has been register successfully", Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity4.this,"Registered Successfully", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        //DITO MAGLALAGAY NUNG PART 2 PLUS TATANGGALIN YUNG USER HAS BEEN REGISTERED SUCCESSFULLY
                        startActivity(new Intent(MainActivity4.this,Employee_Profile.class));

                    }else{
                        Toast.makeText(MainActivity4.this, "Fail to register! Try Again", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}