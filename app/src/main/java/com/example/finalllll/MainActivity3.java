package com.example.finalllll;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextSR,editTextHEA,editTextElemSch,editTextJHSSch,editTextSHSSch, editTextCollSch, editTextElemYrSt, editTextJHSYrSt
            ,editTextSHSYrSt,editTextCollYrSt,editTextElemYrGrad,editTextJHSYrGrad,editTextSHSYrGrad,editTextCollYrGrad,editTextCourseTaken;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar1,progressBar2;

    private int ProgressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mAuth = FirebaseAuth.getInstance();

        //TextView banner = findViewById(R.id.banner);
        //banner.setOnClickListener(this);

        TextView registerUser = findViewById(R.id.registerUser2);
        registerUser.setOnClickListener(this);

        editTextHEA = findViewById(R.id.hea);
        editTextElemSch = findViewById(R.id.elem_sch);
        editTextJHSSch = findViewById(R.id.jhs_sch);
        editTextSHSSch = findViewById(R.id.shs_sch);
        editTextCollSch = findViewById(R.id.coll_sch);
        editTextElemYrSt = findViewById(R.id.elem_yr_st);
        editTextJHSYrSt = findViewById(R.id.jhs_yr_st);
        editTextSHSYrSt = findViewById(R.id.shs_yr_st);
        editTextCollYrSt = findViewById(R.id.coll_yr_st);
        editTextElemYrGrad = findViewById(R.id.elem_yr_grad);
        editTextJHSYrGrad = findViewById(R.id.jhs_yr_grad);
        editTextSHSYrGrad = findViewById(R.id.shs_yr_grad);
        editTextCollYrGrad = findViewById(R.id.coll_yr_grad);
        editTextCourseTaken = findViewById(R.id.course_taken);
        editTextSR = findViewById(R.id.srcode);

        progressBar = findViewById(R.id.progressBar);

        progressBar1 = findViewById(R.id.Progressbar4);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (ProgressStatus < 100){
                    //mProgressStatus += a+b;
                    ProgressStatus=100;
                    android.os.SystemClock.sleep(50);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            progressBar1.setProgress(ProgressStatus);

                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerUser2:
                registerUser2();
                break;

        }
    }

    private void registerUser2() {
        String HEA = editTextHEA.getText().toString().trim();
        String ES = editTextElemSch.getText().toString().trim();
        String JHSS = editTextJHSSch.getText().toString().trim();
        String SHSS = editTextSHSSch.getText().toString().trim();
        String CS = editTextCollSch.getText().toString().trim();
        String EYS = editTextElemYrSt.getText().toString().trim();
        String JHSYS = editTextJHSYrSt.getText().toString().trim();
        String SHSYS = editTextSHSYrSt.getText().toString().trim();
        String CYS = editTextCollYrSt.getText().toString().trim();
        String EYG = editTextElemYrGrad.getText().toString().trim();
        String JHSYG = editTextJHSYrGrad.getText().toString().trim();
        String SHSYG = editTextSHSYrGrad.getText().toString().trim();
        String CYG = editTextCollYrGrad.getText().toString().trim();
        String Course_Taken = editTextCourseTaken.getText().toString().trim();

        if (HEA.isEmpty()) {
            editTextHEA.setError("Highest Educational Attainment is required. Type N/A if none");
            editTextHEA.requestFocus();
            return;
        }

        if (ES.isEmpty()) {
            editTextElemSch.setError("Elementary School is required. Type N/A if none");
            editTextElemSch.requestFocus();
            return;
        }
        if (JHSS.isEmpty()) {
            editTextJHSSch.setError("Junior High School is required. Type N/A if none");
            editTextJHSSch.requestFocus();
            return;
        }
        if (SHSS.isEmpty()) {
            editTextSHSSch.setError("Senior High School is required. Type N/A if none");
            editTextSHSSch.requestFocus();
            return;
        }
        if (CS.isEmpty()) {
            editTextCollSch.setError("College is required. Type N/A if none");
            editTextCollSch.requestFocus();
            return;
        }


        if (EYS.isEmpty()) {
            editTextElemYrSt.setError("Elementary Year Started is required. Type N/A if none");
            editTextElemYrSt.requestFocus();
            return;
        }

        if (EYS.length() != 4) {
            editTextElemYrSt.setError("Year Started must have 4 characters. Type N/A if not applicable");
            editTextElemYrSt.requestFocus();
            return;
        }
        if (EYG.length() != 4) {
            editTextElemYrGrad.setError("Year Graduated must have 4 characters. Type N/A if not applicable");
            editTextElemYrGrad.requestFocus();
            return;
        }

        if (JHSYS.length() != 4) {
            editTextJHSYrSt.setError("Year Started must have 4 characters. Type N/A if not applicable");
            editTextJHSYrSt.requestFocus();
            return;
        }
        if (JHSYG.length() != 4) {
            editTextJHSYrGrad.setError("Year Graduated must have 4 characters. Type N/A if not applicable");
            editTextJHSYrGrad.requestFocus();
            return;
        }

        if (SHSYS.length() != 4) {
            editTextSHSYrSt.setError("Year Started must have 4 characters. Type N/A if not applicable");
            editTextSHSYrSt.requestFocus();
            return;
        }
        if (SHSYG.length() != 4) {
            editTextSHSYrGrad.setError("Year Graduated must have 4 characters. Type N/A if not applicable");
            editTextSHSYrGrad.requestFocus();
            return;
        }
        if (CYS.length() != 4) {
            editTextCollYrSt.setError("Year Started must have 4 characters. Type N/A if not applicable");
            editTextCollYrSt.requestFocus();
            return;
        }
        if (CYG.length() != 4) {
            editTextCollYrGrad.setError("Year Graduated must have 4 characters. Type N/A if not applicable");
            editTextCollYrGrad.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        User2 user2 = new User2(HEA, ES, EYS, EYG, JHSS, JHSYS, JHSYG, SHSS, SHSYS, SHSYG, CS, CYS,
                   CYG, Course_Taken);


        FirebaseDatabase.getInstance().getReference("Users/PART2")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .setValue(user2).addOnCompleteListener(task1 -> {

                    if (task1.isSuccessful()) {
                        //Toast.makeText(Register_User.this,"User has been register successfully", Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity3.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        //DITO MAGLALAGAY NUNG PART 2 PLUS TATANGGALIN YUNG USER HAS BEEN REGISTERED SUCCESSFULLY

                        startActivity(new Intent(MainActivity3.this,MainActivity4.class));

                    } else {
                        Toast.makeText(MainActivity3.this, "Fail to register! Try Again", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

}
