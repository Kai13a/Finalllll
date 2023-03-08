package com.example.finalllll;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    EditText edtEmail, edtPassword;
    ProgressBar progressBar;
    Button signin;

    private FirebaseAuth mAuth;
    ProgressDialog loadingBar;

    AutoCompleteTextView a;

    String Admin_Email = "admin123";
    String Admin_Pass = "pass123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        loadingBar = new ProgressDialog(this);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPass);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        signin = findViewById(R.id.signIn);
        signin.setOnClickListener(this);









    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.register:
                    startActivity(new Intent(this, Register_User.class));
                break;

            case R.id.signIn:
                signin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = edtEmail.getText().toString();
                        String password = edtPassword.getText().toString();


                        //if (email == "admin123" && password == "password123"){

                        //    startActivity(new Intent(MainActivity.this,Admin_Profile.class));
                        //    Toast.makeText(MainActivity.this,"Welcome Admin", Toast.LENGTH_LONG).show();

                        //}





                        signin(email,password);



                    }

                });

                    startActivity(new Intent(MainActivity.this,Employee_Profile.class));
        }
    }


    private void signin(String email, String password) {
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
        }

        if (email.equals(Admin_Email) && password.equals(Admin_Pass)){
            Toast.makeText(this, "HEHEHEHHEHE", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,Admin_Profile.class));

        }

        else{
            loadingBar.setTitle("Sign In");
            loadingBar.setMessage("Please wait..");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {
                        loadingBar.dismiss();
                        FirebaseDatabase.getInstance().getReference("Users/Profile_Images")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        GlobalVar.currentUser = snapshot.getValue(User.class);


                                        a = findViewById(R.id.reg_role);
                                        String b = a.getText().toString().trim();

                                        if(b.equals("Employee")) {
                                            //Toast.makeText(Register_User.this,"User has been register successfully", Toast.LENGTH_LONG).show();
                                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                            //progressBar.setVisibility(View.GONE);


                                            startActivity(new Intent(MainActivity.this, Employee_Profile.class));
                                            Toast.makeText(MainActivity.this, "Successfully sign in", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                        //Intent home = new Intent(MainActivity.this,MainActivity2.class);
                                        //startActivity(home);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }


                                });
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
};
