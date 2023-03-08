package com.example.finalllll;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Faculty_Profile_Tab extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    ImageView left_arrow;
    FloatingActionButton logout;

    private CircleImageView profileImageView;


    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadTask;
    private StorageReference storageProfileRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile_tab);

        //textView = findViewById(R.id.Print_Message);
        //String message = getIntent().getStringExtra("messages");
        //textView.setText(message);

        profileImageView = findViewById(R.id.profile_image);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        storageProfileRef = FirebaseStorage.getInstance().getReference().child("Profile Pic");
        getUserInfo();

        logout = findViewById(R.id.floatingActionButton);
        logout.setOnClickListener(this);
        left_arrow = findViewById(R.id.right);
        left_arrow.setOnClickListener(this);

    }

    private void getUserInfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() && snapshot.getChildrenCount() > 0){
                    if (snapshot.hasChild("image")){
                        String image = snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.right:
                startActivity(new Intent(Faculty_Profile_Tab.this,Faculty_Profile.class));
                break;

            case R.id.floatingActionButton:
                mAuth.signOut();
                logoutUser();
                break;
        }
    }

    private void logoutUser() {
        Intent mainActivity = new Intent(Faculty_Profile_Tab.this, MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
    }
}