package com.example.finalllll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class Faculty_Services_Tab extends AppCompatActivity {

    String[] request_faculty =  {"Maintenance","Technician","Nurse","Professor","Clerk"}; //TO BE ADDED YUNG IBA KUNG MERON PA
    private AutoCompleteTextView autoCompleteTxtRequest_F;
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_services_tab);

        autoCompleteTxtRequest_F = findViewById(R.id.request_faculty);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_items,request_faculty);
        autoCompleteTxtRequest_F.setAdapter(adapterItems);

        autoCompleteTxtRequest_F.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Service: "+item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}