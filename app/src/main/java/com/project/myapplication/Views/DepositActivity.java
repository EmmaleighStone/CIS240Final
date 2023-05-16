package com.project.myapplication.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.myapplication.Controller.AppController;
import com.project.myapplication.R;

import java.text.DecimalFormat;
import com.project.myapplication.Models.Account;
import com.project.myapplication.Controller.AppController;
public class DepositActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppController appController = new AppController(DepositActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        DecimalFormat df = new DecimalFormat( "#.00" );
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        int accountId = sharedPreferences.getInt("accountId", 99);
        TextView txtBalanceAmt = (TextView) findViewById(R.id.txtBalanceAmtD);
        EditText editDeposit = (EditText) findViewById(R.id.editDeposit);
        Button btnDeposit = (Button) findViewById(R.id.btnDepositNow);
        txtBalanceAmt.setText("$" + df.format(appController.getAccount(accountId).getBalance()));

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double depositAmt = Double.parseDouble(editDeposit.getText().toString());
                appController.depositToAccount(accountId, depositAmt);
                startActivity(new Intent(DepositActivity.this, HomeActivity.class));
            }
        });



    }
}