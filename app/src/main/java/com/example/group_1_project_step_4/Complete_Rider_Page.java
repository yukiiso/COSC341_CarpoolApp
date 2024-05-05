package com.example.group_1_project_step_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Complete_Rider_Page extends AppCompatActivity {

    private DatabaseReference root;
    private int confirm_code;
    private TextView code;
    private String id;
    ArrayList<String> userName =  new ArrayList<>();
    ArrayList<String> status =  new ArrayList<>();
    ArrayList<String> currLocation =  new ArrayList<>();
    ArrayList<String> destination =  new ArrayList<>();
    ArrayList<String> departDate =  new ArrayList<>();
    ArrayList<String> departTime =  new ArrayList<>();
    ArrayList<String> numOfPassenger =  new ArrayList<>();
    ArrayList<String> gender =  new ArrayList<>();
    ArrayList<String> smoking =  new ArrayList<>();
    ArrayList<String> car =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_rider);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        confirm_code = bundle.getInt("random");

        code = findViewById(R.id.conf_code);
        code.setText(Integer.toString(confirm_code));

        id = bundle.getString("username");

        // Get an instance of database
        root = FirebaseDatabase.getInstance().getReference();
        readDataFromDatabase();
    }

    public void toastMessage(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        toast.show();
    }
    public void submit (View view) {
        DatabaseReference request = root.child(userName.get(0));
        request.child("status").setValue(null);
        request.child("from").setValue(null);
        request.child("to").setValue(null);
        request.child("departOn").setValue(null);
        request.child("departAt").setValue(null);
        request.child("numPassenger").setValue(null);
        request.child("gender").setValue(null);
        request.child("smoking").setValue(null);
        request.child("car").setValue(null);
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }

    public void readDataFromDatabase() {

        OnCompleteListener<DataSnapshot> onValuesFetched = task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else
            {
                DataSnapshot receivedValue = task.getResult();
                for(DataSnapshot node: receivedValue.getChildren())
                {
                    userName.add(node.getKey());
                    status.add(node.child("status").getValue().toString());
                    currLocation.add(node.child("from").getValue().toString());
                    destination.add(node.child("to").getValue().toString());
                    departDate.add(node.child("departOn").getValue().toString());
                    departTime.add(node.child("departAt").getValue().toString());
                    numOfPassenger.add(node.child("numPassenger").getValue().toString());
                    gender.add(node.child("gender").getValue().toString());
                    smoking.add(node.child("smoking").getValue().toString());
                    car.add(node.child("car").getValue().toString());
                }
            }
        };
        root.get().addOnCompleteListener(onValuesFetched);
    }

    public void account (View view){
        Intent intent = new Intent(this, Account_Page.class);
        startActivity(intent);
    }
    public void home (View view){
        Intent intent = new Intent(this, Main_Page.class);
        startActivity(intent);
    }
    public void search_ride (View view){
        Intent intent = new Intent(this, Request_Page.class);
        startActivity(intent);
    }
    public void payment (View view){
        Intent intent = new Intent(this, Account_Payment_Info_Page.class);
        startActivity(intent);
    }
    public void message (View view){
        Intent intent = new Intent(this, Message_Page.class);
        startActivity(intent);
    }
}