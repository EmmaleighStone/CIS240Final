package com.project.myapplication.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.myapplication.Controller.AppController;
import com.project.myapplication.R;

import java.text.DecimalFormat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppController appController = new AppController(HomeActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView imageView = (ImageView) findViewById(R.id.imgHome);
        imageView.getLayoutParams().width = 150;
        imageView.getLayoutParams().height = 150;
        DecimalFormat df = new DecimalFormat( "#.00" );
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        int accountId = sharedPreferences.getInt("accountId", 0);
        TextView txtWelcomeUser = (TextView) findViewById(R.id.txtWelcomeUser);
        TextView txtBalanceAmt = (TextView) findViewById(R.id.txtBalanceAmt);
        Button btnDeposit = (Button) findViewById(R.id.btnDeposit);
        Button btnWithdrawal = (Button) findViewById(R.id.btnWithdrawal);

        txtWelcomeUser.setText("Welcome " + appController.getAccount(accountId).getFirstName() +
                " " + appController.getAccount(accountId).getLastName() + "!");
        txtBalanceAmt.setText("$" + df.format(appController.getAccount(accountId).getBalance()));

        /*txtWelcomeUser.setText("Welcome " + AppController.accounts.get(accountId).firstName + " " + AppController.accounts.get(accountId).lastName + "!");
        txtBalanceAmt.setText("$" + df.format(AppController.accounts.get(accountId).balance));*/
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DepositActivity.class));
            }
        });
        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, WithdrawalActivity.class));
            }
        });
    }
}