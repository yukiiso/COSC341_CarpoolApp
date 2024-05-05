package com.example.group_1_project_step_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Message_Text_Page extends AppCompatActivity {

    private int requestNum=0;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_text);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 1 = ride request, 2 = offer request
        requestNum = bundle.getInt("request");
    }
    public void completeRide(View view) {
        Intent intent;
        Bundle bundle = new Bundle();
        int random = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
        if (requestNum == 1) {
            intent = new Intent(this, Complete_Rider_Page.class);
            bundle.putInt("random", random);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            intent = new Intent(this, Complete_Offer_Page.class);
            bundle.putInt("random", random);
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
        startActivity(intent);
    }
}