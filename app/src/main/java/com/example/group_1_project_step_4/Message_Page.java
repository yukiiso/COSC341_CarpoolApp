package com.example.group_1_project_step_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Message_Page extends AppCompatActivity {
    private int requestNum;
    private String username;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 0 = not initialized, 1 = ride request, 2 = offer request
        requestNum = bundle.getInt("request");
    }

    public void toastMessage(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        toast.show();
    }
    public void message1(View view) {
        Intent intent = new Intent(this, Message_Text_Page.class);
        Bundle bundle = new Bundle();
        bundle.putInt("request", requestNum);
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
