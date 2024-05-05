package com.example.group_1_project_step_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Order_Summary_Page extends AppCompatActivity {
    private TextView basic_fee;
    private TextView distance;
    private TextView subtotal;
    private TextView gst_pst;
    private TextView total;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_summary);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String distnc = bundle.getString("distance");
        basic_fee = findViewById(R.id.basic_fee_text);
        distance = findViewById(R.id.distance_text);
        subtotal = findViewById(R.id.subtotal_text);
        gst_pst = findViewById(R.id.gst_pst_text);
        total = findViewById(R.id.total_text);

        String distance_db = "20";

        double basic = 10;
        double dist = Double.parseDouble(distnc);
        double subtot = dist * 0.36;
        double tax = subtot * 0.12;
        double tot = subtot + tax;

        basic_fee.setText(String.format("$ %.2f",basic));
        distance.setText(String.format("%.2f km", dist));
        subtotal.setText(String.format("$ %.2f", subtot));
        gst_pst.setText(String.format("$ %.2f", tax));
        total.setText(String.format("$ %.2f", tot));
    }
    public void change_payment (View view) {
        Intent intent = new Intent(this, Account_Payment_Info_Page.class);
        startActivity(intent);
    }
    public void submit (View view){
        Intent intent = new Intent(this, Message_Page.class);
        Bundle bundle = new Bundle();
        bundle.putInt("request", 1);
        intent.putExtras(bundle);
        startActivity(intent);
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
