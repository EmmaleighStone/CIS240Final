package com.project.myapplication.Controller;

import android.content.SharedPreferences;
import android.widget.TextView;

import com.project.myapplication.Models.Account;

import java.util.ArrayList;

public class AppController {

    public static ArrayList<Account> accounts = new ArrayList<Account>();


    public static void addAccount(ArrayList<Account> accounts, Account account)
    {
        int end = accounts.size();
        account.accountId = end;
        accounts.add(end, account);
    }

    public static boolean checkLogin(ArrayList<Account> accounts, String username, String password, TextView txtError)
    {
        boolean success = false;
        if (username == null || password == null)
        {
            txtError.setText("You did not enter a username and/or password. Please try again.");
        }
        else
        {
            for (int i = 0; i < accounts.size(); i++)
            {
                if (username.equals(accounts.get(i).username) && password.equals(accounts.get(i).password))
                {
                    success = true;
                }
            }
        }
        return success;
    }
    public static int checkAccountId(ArrayList<Account> accounts, String username, String password)
    {
        int accountId = 99;
        for (int i = 0; i < accounts.size(); i++)
        {
            if (username.equals(accounts.get(i).username) && password.equals(accounts.get(i).password))
            {
                accountId = i;
            }
        }
        return accountId;
    }
    public static void depositAccount(ArrayList<Account> accounts, int accountId, double depositAmt)
    {
        Account account = accounts.get(accountId);
        Double newBalance = account.balance + depositAmt;
        account.balance = newBalance;
    }

    public static void withdrawalAccount(ArrayList<Account> accounts, int accountId, double withdrawalAmt)
    {
        Account account = accounts.get(accountId);
        Double newBalance = account.balance - withdrawalAmt;
        account.balance = newBalance;
    }

    public static void makeDummyData(ArrayList<Account> accounts)
    {
        AppController.addAccount(accounts, new Account("John", "Doe", "johndoe", "1234", 200.00));
        AppController.addAccount(accounts, new Account("Mary", "Smith", "marysmith", "5678", 960.66));
        AppController.addAccount(accounts, new Account("Marcus", "Stone", "marcusstone", "1111", 14.28));
    }

}
