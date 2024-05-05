package com.example.group_1_project_step_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main_Page extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void search_ride (View view){
        Intent intent = new Intent(this, Request_Page.class);
        Bundle bundle = new Bundle();
        bundle.putInt("request", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void offer_ride (View view){
        Intent intent = new Intent(this, Request_Page.class);
        Bundle bundle = new Bundle();
        bundle.putInt("request", 2);
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

    public void payment (View view){
        Intent intent = new Intent(this, Account_Payment_Info_Page.class);
        startActivity(intent);
    }

    public void message (View view){
        Intent intent = new Intent(this, Message_Page.class);
        Bundle bundle = new Bundle();
        bundle.putInt("request", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
