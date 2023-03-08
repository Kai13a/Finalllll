package com.example.finalllll;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class Register_User extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextFirstName,editTextMiddleName,editTextLastName,editTextSRCode, editTextAge, editTextEmail, editTextPassword
            ,editTextDateofBirth,editTextMobileNumber,editTextPlaceofOrigin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    String[] gender =  {"Male","Female","Other"};
    String[] civil_status = {"Single","Married","Divorce","Widowed","Separated"};
    private AutoCompleteTextView autoCompleteTxtGender,autoCompleteTextViewCivil;

    String[] role = {"Employee", "Faculty", "Admin"};
    String[] campus = {"Alangilan", "Balayan", "Lemery", "Lipa", "Lobo", "Mabini", "Malvar", "Nasugbu", "Pablo Borbon", "Rosario", "San Juan"};
    public static AutoCompleteTextView autoCompleteTxtRole;
    private AutoCompleteTextView autoCompleteTxtCampus;

    ArrayAdapter<String> adapterItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        TextView banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);

        TextView registerUser = findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFirstName = findViewById(R.id.firstname);
        editTextMiddleName = findViewById(R.id.middlename);
        editTextLastName = findViewById(R.id.lastname);
        editTextSRCode = findViewById(R.id.srcode);
        editTextAge = findViewById(R.id.age);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextDateofBirth = findViewById(R.id.dateofbirth);
        editTextMobileNumber = findViewById(R.id.mobile_number);
        editTextPlaceofOrigin = findViewById(R.id.placeoforigin);


        progressBar = findViewById(R.id.progressBar);

        autoCompleteTxtGender = findViewById(R.id.gender);
        autoCompleteTextViewCivil = findViewById(R.id.civil_status);


        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,gender);
        autoCompleteTxtGender.setAdapter(adapterItems);

        autoCompleteTxtGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,civil_status);
        autoCompleteTextViewCivil.setAdapter(adapterItems);
        autoCompleteTextViewCivil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

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
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstname = editTextFirstName.getText().toString().trim();
        String middlename = editTextMiddleName.getText().toString().trim();
        String lastname = editTextLastName.getText().toString().trim();
        String srcode = editTextSRCode.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String dob = editTextDateofBirth.getText().toString().trim();
        String mobilenum = editTextMobileNumber.getText().toString().trim();
        String poo = editTextPlaceofOrigin.getText().toString().trim();
        String gender = autoCompleteTxtGender.getText().toString().trim();
        String civil = autoCompleteTextViewCivil.getText().toString().trim();
        String role = autoCompleteTxtRole.getText().toString().trim();
        String campus = autoCompleteTxtCampus.getText().toString().trim();
        String myValue = srcode;
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("myValue", srcode);
        if(firstname.isEmpty()){
            editTextFirstName.setError("First name is required");
            editTextFirstName.requestFocus();
            return;
        }
        if(middlename.isEmpty()){
            editTextMiddleName.setError("Middle name is required");
            editTextMiddleName.requestFocus();
            return;
        }

        if(lastname.isEmpty()){
            editTextFirstName.setError("Last name is required");
            editTextFirstName.requestFocus();
            return;
        }
        if(srcode.length() != 7){//PWEDE PALITAN KUNG MAY CONFLICT
            editTextSRCode.setError("SR-CODE must have 7 characters");
            editTextSRCode.requestFocus();
            return;
        }
        if(srcode.isEmpty()){
            editTextSRCode.setError("SR-CODE is required");
            editTextSRCode.requestFocus();
            return;
        }
        if(age.isEmpty()){
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() <6){
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

       if(dob.isEmpty()){
           editTextDateofBirth.setError("Date of birth is required");
           editTextDateofBirth.requestFocus();
           return;
       }
        if(mobilenum.length() != 11){
            editTextMobileNumber.setError("Mobile number must have 11 numeric characters");
            editTextMobileNumber.requestFocus();
            return;
        }
        if(mobilenum.isEmpty()){
            editTextMobileNumber.setError("Mobile numnber is required");
            editTextMobileNumber.requestFocus();
            return;
        }
        if(poo.isEmpty()){
            editTextPlaceofOrigin.setError("Place of origin is required");
            editTextPlaceofOrigin.requestFocus();
            return;
        }

        if(gender.isEmpty()){
            autoCompleteTxtGender.setError("Gender is required");
            autoCompleteTxtGender.requestFocus();
            return;
        }

        if(civil.isEmpty()){
            autoCompleteTxtGender.setError("Civil Status is required");
            autoCompleteTxtGender.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()){
                        User user = new User(firstname,middlename,lastname,srcode,age,email,dob,mobilenum,poo,gender,civil,role,campus);
                        //////////////

                        /////////////////

                        FirebaseDatabase.getInstance().getReference("Users/PART1")
                                .child(myValue)//Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {

                                    if(task1.isSuccessful()){
                                        //Toast.makeText(Register_User.this,"User has been register successfully", Toast.LENGTH_LONG).show();
                                        Toast.makeText(Register_User.this,"Data Inserted", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);


                                        //Intent intent = new Intent(Register_User.this,Employee_Profile_Tab.class);
                                        //String message = editTextAge.getText().toString().trim();
                                        //intent.putExtra("messages",message);
                                        //startActivity(intent);

                                        //DITO MAGLALAGAY NUNG PART 2 PLUS TATANGGALIN YUNG USER HAS BEEN REGISTERED SUCCESSFULLY
                                        startActivity(new Intent(this,MainActivity3.class));
                                        ///////////////////



                                    }else{
                                        Toast.makeText(Register_User.this, "Fail to register! Try Again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });
                    }else{
                        Toast.makeText(Register_User.this, "Failed to register", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}