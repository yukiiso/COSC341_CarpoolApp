package com.example.group_1_project_step_4;

import static android.app.ProgressDialog.show;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Request_Page extends AppCompatActivity {
    private DatabaseReference root; // Global variable for database reference
    private EditText user_name, current_location, destination, date, time, dist;
    private String username, from, to, departOn, departAt, distance;
    private TextView num_passenger;
    private int numPassenger;
    private RadioGroup gender_pref, smoking_pref, car_pref;
    private RadioButton gender_prf, smoking_prf, car_prf;
    private String gender, smoke, car;
    private int num_pass = 0;
    private int requestNum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 1 creates a ride request and 2 creates a offer request
        requestNum = bundle.getInt("request");

        // Get an instance of database
        root = FirebaseDatabase.getInstance().getReference();

        user_name = findViewById(R.id.usrnam);
        current_location = findViewById(R.id.current_location_text);
        destination = findViewById(R.id.destination_text);
        date = findViewById(R.id.date_text);
        time = findViewById(R.id.time_text);
        dist = findViewById(R.id.dist);
        num_passenger = findViewById(R.id.num_passenger_text);
        gender_pref = findViewById(R.id.gender_pref_radio);
        smoking_pref = findViewById(R.id.smoking_preference_radio);
        car_pref = findViewById(R.id.car_preference_radio);
    }
    public void minus_passenger_button (View view){
        if (num_pass > 0)
            num_pass--;
        num_passenger.setText(String.valueOf(num_pass));
    }
    public void add_passenger_button (View view){
        num_pass++;
        num_passenger.setText(String.valueOf(num_pass));
    }
    public void setDate(View view) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,

                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    public void setTime(View view) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public boolean checkEmpty (String str) {
        if (str.isEmpty())
            return false;
        else {

            return true;
        }
    }
    public boolean checkRadioButton (RadioButton radio_button){
        try {
            if (radio_button != null && radio_button.isChecked())  // gender selected
                return true;
            else                                                 // gender not selected
                return false;
        } catch (Exception e) {

        }
        return false;
    }
    public void toastMessage(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        toast.show();
    }
    public void submit (View view){
        username = user_name.getText().toString();
        from = current_location.getText().toString();
        to = destination.getText().toString();
        departOn = date.getText().toString();
        departAt = time.getText().toString();
        distance = dist.getText().toString();
        numPassenger = Integer.parseInt(num_passenger.getText().toString());

        int selectedId = gender_pref.getCheckedRadioButtonId();
        try {
            gender_prf = findViewById(selectedId);
            gender = gender_prf.getText().toString();
        } catch (Exception e) {

        }

        selectedId = smoking_pref.getCheckedRadioButtonId();
        try {
            smoking_prf = findViewById(selectedId);
            smoke = smoking_prf.getText().toString();
        } catch (Exception e) {

        }

        selectedId = car_pref.getCheckedRadioButtonId();
        try {
            car_prf = findViewById(selectedId);
            car = car_prf.getText().toString();
        } catch (Exception e) {

        }

        int error = 0;
        if (!checkEmpty(username)) {
            error = 0;
            toastMessage("Please enter your username");
            error++;
        } else if (!checkEmpty(from)) {
            error = 0;
            toastMessage("Please enter your current location");
            error++;
        } else if (!checkEmpty(to)) {
            error = 0;
            toastMessage("Please enter your destination");
            error++;
        } else if (!checkEmpty(distance)) {
            error = 0;
            toastMessage("Please enter a distance of your travel");
            error++;
        } else if (!checkEmpty(departOn)) {
            error = 0;
            toastMessage("Please enter a depart date");
            error++;
        } else if (!checkEmpty(departAt)) {
            error = 0;
            toastMessage("Please enter a depart time");
            error++;
        } else if (numPassenger <= 0) {
            toastMessage("Please select a number of passengers");
            error++;
        } else if (!checkRadioButton(gender_prf)) {
            error = 0;
            toastMessage("Please select a gender preference");
            error++;
        } else if (!checkRadioButton(smoking_prf)) {
            error = 0;
            toastMessage("Please select a smoking preference");
            error++;
        } else if (!checkRadioButton(car_prf)) {
            error = 0;
            toastMessage("Please select a car type");
            error++;
        }

        if(error < 1) {
            Intent intent;
            if (requestNum == 1){
                intent= new Intent(this, Order_Summary_Page.class);
            } else{
                intent = new Intent(this, Ride_Offer_Page.class);
            }

            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putString("from", from);
            bundle.putString("to", to);
            bundle.putString("distance", distance);
            bundle.putString("departOn", departOn);
            bundle.putString("departAt", departAt);
            bundle.putInt("numPassenger", numPassenger);
            bundle.putString("gender", gender);
            bundle.putString("smoking", smoke);
            bundle.putString("car", car);

            String status = "";
            if (requestNum == 1)
                status = "rider";
            else
                status = "offer";

            // Write on database
            DatabaseReference request = root.child(username);
            request.child("status").setValue(status);
            request.child("from").setValue(from);
            request.child("to").setValue(to);
            request.child("departOn").setValue(departOn);
            request.child("departAt").setValue(departAt);
            request.child("numPassenger").setValue(numPassenger);
            request.child("gender").setValue(gender);
            request.child("smoking").setValue(smoke);
            request.child("car").setValue(car);

            intent.putExtras(bundle);
            startActivity(intent);
        }

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
        Bundle bundle = new Bundle();
        bundle.putInt("random", 0);
        startActivity(intent);
    }
}
