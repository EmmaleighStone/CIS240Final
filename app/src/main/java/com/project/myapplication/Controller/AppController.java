package com.project.myapplication.Controller;

import android.widget.TextView;
import com.project.myapplication.Models.Account;
import com.project.myapplication.Models.Transaction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AppController {

    public static ArrayList<Account> accounts = new ArrayList<Account>();


    public ArrayList<String> getFragments()
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Balance Inquiry");
        list.add("Make Withdrawal");
        list.add("Make Deposit");
        return list;
    }

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
    public static int getAccountId(ArrayList<Account> accounts, String username, String password)
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

    public static Account getAccount(int accountId)
    {
        Account account = new Account();
        for (int i = 0; i < accounts.size(); i++)
        {
            if (accountId == (accounts.get(i).accountId))
            {
                account = accounts.get(i);
            }
        }
        return account;
    }

    public static void addTransaction(Account account, Transaction transaction)
    {
        transaction.accountId = account.accountId;
        int end = account.transactions.size();
        transaction.transactionId = end;
        account.transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions(int accountId)
    {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        Account account = getAccount(accountId);
        for (int i = 0; i < account.transactions.size(); i++)
        {
            transactions.add(account.transactions.get(i));
        }

        return transactions;
    }
    public static void depositAccount(ArrayList<Account> accounts, int accountId, double depositAmt)
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy \t\t\t HH:mm");
        Date date = new Date();
        Transaction transaction = new Transaction(accountId, depositAmt, "Deposit", dateFormat.format(date));
        Account account = accounts.get(accountId);
        addTransaction(account, transaction);
        Double newBalance = account.balance + depositAmt;
        account.balance = newBalance;
    }

    public static boolean withdrawalAccount(ArrayList<Account> accounts, int accountId, double withdrawalAmt)
    {
        Account account = accounts.get(accountId);
        if (account.balance - withdrawalAmt > 0)
        {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy \t\t\t HH:mm");
            Date date = new Date();
            Transaction transaction = new Transaction(accountId, withdrawalAmt, "Withdrawal", dateFormat.format(date));
            addTransaction(account, transaction);
            Double newBalance = account.balance - withdrawalAmt;
            account.balance = newBalance;
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void makeDummyData(ArrayList<Account> accounts)
    {
        AppController.addAccount(accounts, new Account("Kirsten", "Markley", "markley", "aloha", 100000.00));
        AppController.addAccount(accounts, new Account("John", "Doe", "johndoe", "1234", 200.00));
        AppController.addAccount(accounts, new Account("Mary", "Smith", "marysmith", "5678", 960.66));
        AppController.addAccount(accounts, new Account("Marcus", "Stone", "marcusstone", "1111", 14.28));
    }

}
