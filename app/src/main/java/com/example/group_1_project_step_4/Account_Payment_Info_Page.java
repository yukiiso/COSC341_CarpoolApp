package com.example.group_1_project_step_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account_Payment_Info_Page extends AppCompatActivity {
    private Spinner spinner;
    private TextView username, addr, email_address, phone_number, card_number, card_exp, bill_address;
    private String firstName, lastName, address, emailAddress, phoneNumber, cardNumber, cardExpiry, billAddress;
    private CheckBox same, terms_cond;
    private boolean sameBill, termsCond;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_payment_info);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.creditArray, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);

        username = findViewById(R.id.user_name_text);
        addr = findViewById(R.id.address_text);
        email_address = findViewById(R.id.email_address_text);
        phone_number = findViewById(R.id.phone_number_text);
        card_number = findViewById(R.id.card_number_text);
        card_exp = findViewById(R.id.card_expiry_text);
        bill_address = findViewById(R.id.card_billing_text);
        same = findViewById(R.id.billing_same);
        terms_cond = findViewById(R.id.agree);
    }
    public boolean checkEmpty(String str) {
        if (str.isEmpty())
            return false;
        else
            return true;
    }
    public boolean checkValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean checkValidNumber(String num, int length) {
        if (num.length() != length)
            return false;
        else
            return true;
    }
    public void toastMessage(String message) {
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        toast.show();
    }

    public void submit(View view) {
        firstName = username.getText().toString();
        address = addr.getText().toString();
        emailAddress = email_address.getText().toString();
        phoneNumber = phone_number.getText().toString();
        cardNumber = card_number.getText().toString();
        cardExpiry = card_exp.getText().toString();
        billAddress = bill_address.getText().toString();
        sameBill = same.isChecked();
        termsCond = terms_cond.isChecked();

        int error = 0;
        if (!checkEmpty(firstName)) {
            error = 0;
            toastMessage("Please enter your first name");
            error++;
        } else if (!checkEmpty(lastName)) {
            error = 0;
            toastMessage("Please enter your last name");
            error++;
        } else if (!checkEmpty(address)) {
            error = 0;
            toastMessage("Please enter your address");
            error++;
        } else if (!checkEmpty(emailAddress)) {
            toastMessage("Please enter your email address");
            error++;
        } else if (!checkValidEmail(emailAddress)) {
            error = 0;
            toastMessage("Please enter a valid email address");
            error++;
        } else if (!checkEmpty(phoneNumber)) {
            error = 0;
            toastMessage("Please enter your phone number");
            error++;
        } else if (!checkValidNumber(phoneNumber, 10)) {
            error = 0;
            toastMessage("Please enter a valid phone number");
            error++;
        } else if (!checkEmpty(cardNumber)) {
            error = 0;
            toastMessage("Please enter your payment information");
            error++;
        } else if (!checkValidNumber(cardNumber, 16)) {
            error = 0;
            toastMessage("Please enter a valid card number");
            error++;
        } else if (!checkEmpty(cardExpiry)) {
            error = 0;
            toastMessage("Please enter your card expiry date");
            error++;
        } else if (!checkValidNumber(cardExpiry, 4)) {
            error = 0;
            toastMessage("Please enter a valid card expiry date");
            error++;
        } else if (!sameBill) {
            if (!checkEmpty(billAddress)) {
                error = 0;
                toastMessage("Please enter your billing address");
                error++;
            }
        } else if (!termsCond) {
            error = 0;
            toastMessage("Please agree on terms & conditions");
            error++;
        }
        if (sameBill) {
            bill_address.setText(address);
        }

        if(error < 1) {
            Intent intent = new Intent(this, Order_Summary_Page.class);

            Bundle bundle = new Bundle();
            bundle.putString("firstName", firstName);
            bundle.putString("lastName", lastName);
            bundle.putString("address", address);
            bundle.putString("emailAddress", emailAddress);
            bundle.putString("phoneNumber", phoneNumber);
            bundle.putString("cardNumber", cardNumber);
            bundle.putString("cardExpiry", cardExpiry);
            bundle.putString("billAddress", billAddress);

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
