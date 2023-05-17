package com.project.myapplication.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.project.myapplication.Controller.AppController;
import com.project.myapplication.Models.Transaction;
import com.project.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HomeFragment extends NavHostFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout layout = (LinearLayout) parentView;

        AppController appController = new AppController();
        DecimalFormat df = new DecimalFormat("#.00");
        SharedPreferences sharedPreferences = this.requireActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        int accountId = sharedPreferences.getInt("accountId", 99);
        ImageView imageView = (ImageView) parentView.findViewById(R.id.imgHome);
        imageView.getLayoutParams().width = 500;
        imageView.getLayoutParams().height = 500;
        TextView txtWelcomeUser = (TextView) parentView.findViewById(R.id.txtWelcomeUser);
        txtWelcomeUser.setText("Welcome " + appController.getAccount(accountId).firstName + " " + appController.getAccount(accountId).lastName + "!");
        TextView txtBalanceAmt = (TextView) parentView.findViewById(R.id.txtBalanceAmt);
        TextView transactionHeaders = (TextView) parentView.findViewById(R.id.txtTransactionsHeader);
        txtBalanceAmt.setText("Account Balance: \t\t $" + df.format(appController.getAccount(accountId).balance));
        ArrayList<Transaction> transactions = appController.getTransactions(accountId);
        if (transactions.size() == 0)
        {
            transactionHeaders.setText("\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tno recent transactions");
        }
        else
        {
            transactionHeaders.setText("\t\t\t\t\t\tType\t\t\t\t\t\tAmount\t\t\t\t\t\tDate\t\t\t\t\t\tTime");
        }
        for (Transaction transaction : transactions) {
            TextView textView = new TextView(getContext());
            String spaces = "\t";
            if (transaction.type.equals("Withdrawal"))
            {
                spaces = "\t\t\t";
            }
            else
            {
                spaces = "\t\t\t\t\t";
            }
            String textString = spaces + transaction.type + "\t\t\t\t $" + df.format(transaction.amount) + "\t\t\t\t" + transaction.date;
            textView.setText(textString);
            layout.addView(textView);

            }

        return parentView;
    }
}