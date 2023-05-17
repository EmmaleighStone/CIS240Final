package com.project.myapplication.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.myapplication.Controller.AppController;
import com.project.myapplication.R;

import java.text.DecimalFormat;

public class DepositFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deposit, container, false);

        AppController appController = new AppController();
        DecimalFormat df = new DecimalFormat("#.00");
        SharedPreferences sharedPreferences = this.requireActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        int accountId = sharedPreferences.getInt("accountId", 99);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imgDeposit);
        imageView.getLayoutParams().width = 500;
        imageView.getLayoutParams().height = 500;
        TextView txtBalanceAmt = (TextView) rootView.findViewById(R.id.txtBalanceAmtD);
        EditText editDeposit = (EditText) rootView.findViewById(R.id.editDeposit);
        Button btnDeposit = (Button) rootView.findViewById(R.id.btnDepositNow);
        txtBalanceAmt.setText("$" + df.format(AppController.accounts.get(accountId).balance));

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double depositAmt = Double.parseDouble(editDeposit.getText().toString());
                AppController.depositAccount(AppController.accounts, accountId, depositAmt);
                Toast.makeText(getContext(), "Deposit made! Check your balance now!", Toast.LENGTH_LONG).show();
                txtBalanceAmt.setText("$" + df.format(AppController.accounts.get(accountId).balance));
                editDeposit.setText("");
            }
        });
        return rootView;

    }
}