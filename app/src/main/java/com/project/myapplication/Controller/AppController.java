package com.project.myapplication.Controller;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.project.myapplication.Models.Account;
import com.project.myapplication.Models.Transaction;
import com.project.myapplication.Repos.BankRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppController {

    private BankRepository mBankRepo;

    public AppController(Context context)
    {
        mBankRepo = BankRepository.getInstance(context);
    }

    public List<Account> getAccounts() {
        return mBankRepo.getAccounts();
    }

    public Account getAccount(long accountId) {
        return mBankRepo.getAccount(accountId);
    }

    public void addAccount(Account account) {
        mBankRepo.addAccount(account);
    }

    public void updateAccount(Account account) {
        mBankRepo.updateAccount(account);
    }

    public List<Transaction> getTransactions(Account account)
    {
        return mBankRepo.getTransactions(account.getId());
    }

    public void addTransaction(Transaction transaction) {
        mBankRepo.addTransaction(transaction);
    }

    public long loginUser(String username, String password)
    {

        List<Account> accounts = mBankRepo.getAccounts();
        for (int i = 0; i < accounts.size(); i++)
        {
            if (accounts.get(i).getUsername() == username && accounts.get(i).getPassword() == password)
            {
                return accounts.get(i).getId();
            }
        }
        return 0;
    }

    public void withdrawalFromAccount(long accountId, Double withdrawalAmt)
    {
        Account account = this.getAccount(accountId);
        Transaction transaction = new Transaction();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        transaction.setDate(dateFormat.format(date));
        transaction.setType("withdrawal");
        transaction.setAmount(withdrawalAmt);
        transaction.setAccountId(accountId);
        mBankRepo.addTransaction(transaction);
        account.setBalance(account.getBalance() - withdrawalAmt);
        mBankRepo.updateAccount(account);
    }

    public void depositToAccount(long accountId, Double depositAmt)
    {
        Account account = this.getAccount(accountId);
        Transaction transaction = new Transaction();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        transaction.setDate(dateFormat.format(date));
        transaction.setType("deposit");
        transaction.setAmount(depositAmt);
        transaction.setAccountId(accountId);
        mBankRepo.addTransaction(transaction);
        account.setBalance(account.getBalance() + depositAmt);
        mBankRepo.updateAccount(account);
    }

    /*public static ArrayList<Account> accounts = new ArrayList<Account>();


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
    }*/

}
