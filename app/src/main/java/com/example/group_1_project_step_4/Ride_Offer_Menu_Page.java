package com.example.group_1_project_step_4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Ride_Offer_Menu_Page extends AppCompatActivity {

    private DatabaseReference root; // Global variable for database reference
    private TextView username, pick_up, drop_off, departure_date_time, num_passenger, gender_pref, car_pref, smoking_pref;
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
    int counter = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ride_offer_menu);

        // Get an instance of database
        root = FirebaseDatabase.getInstance().getReference();

        username = findViewById(R.id.username_text);
        pick_up = findViewById(R.id.pick_up_text);
        drop_off = findViewById(R.id.drop_off_text);
        departure_date_time = findViewById(R.id.departure_date_time_text);
        num_passenger = findViewById(R.id.num_pass_text);
        gender_pref = findViewById(R.id.gender_pref_text);
        car_pref = findViewById(R.id.car_pref_text);
        smoking_pref = findViewById(R.id.smoking_pref_text);

        // Read data from firebase
        readDataFromDatabase();
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
//                    userName.add(node.child("username").getValue().toString());
                    status.add(node.child("status").getValue().toString());
                    currLocation.add(node.child("from").getValue().toString());
                    destination.add(node.child("to").getValue().toString());
                    departDate.add(node.child("departOn").getValue().toString());
                    departTime.add(node.child("departAt").getValue().toString());
                    numOfPassenger.add(node.child("numPassenger").getValue().toString());
                    gender.add(node.child("gender").getValue().toString());
                    smoking.add(node.child("smoking").getValue().toString());
                    car.add(node.child("car").getValue().toString());
                    counter++;
                }
                if (counter == 0) {
                    toastMessage("Please add some record");
                } else {
                    username.setText(userName.get(0));
                    pick_up.setText(currLocation.get(0));
                    drop_off.setText(destination.get(0));
                    departure_date_time.setText(departDate.get(0) + " " + departTime.get(0));
                    num_passenger.setText(numOfPassenger.get(0));
                    gender_pref.setText(gender.get(0));
                    car_pref.setText(smoking.get(0));
                    smoking_pref.setText(car.get(0));

                }
            }
        };
        root.get().addOnCompleteListener(onValuesFetched);
    }

    public void toastMessage(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        toast.show();
    }
    public void accept (View view){
        Intent intent = new Intent(this, Message_Page.class);
        Bundle bundle = new Bundle();
        bundle.putInt("request", 2);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void deny (View view){
        Intent intent = new Intent(this, Ride_Offer_Page.class);
        startActivity(intent);
    }
    public void back (View view){
        finish();
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
