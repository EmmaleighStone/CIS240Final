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

public class WithdrawalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        DecimalFormat df = new DecimalFormat( "#.00" );
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        int accountId = sharedPreferences.getInt("accountId", 99);
        TextView txtBalanceAmt = (TextView) findViewById(R.id.txtBalanceAmtW);
        EditText editWithdrawal = (EditText) findViewById(R.id.editWithdrawal);
        Button btnWithdrawal = (Button) findViewById(R.id.btnWithdrawalNow);
        txtBalanceAmt.setText("$" + df.format(AppController.accounts.get(accountId).balance));

        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double withdrawalAmt = Double.parseDouble(editWithdrawal.getText().toString());
                AppController.withdrawalAccount(AppController.accounts, accountId, withdrawalAmt);
                startActivity(new Intent(WithdrawalActivity.this, HomeActivity.class));
            }
        });
    }
}