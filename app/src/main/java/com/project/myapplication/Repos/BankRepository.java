package com.project.myapplication.Repos;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.project.myapplication.Models.Transaction;
import com.project.myapplication.Models.Account;
import java.util.List;

public class BankRepository {
    private AccountDao accountDao;
    private TransactionDao transactionDao;
    private LiveData<List<Account>> allAccounts;
    private LiveData<List<Transaction>> allTransactions;

    public BankRepository(Application application) {
        BankDatabase database = BankDatabase.getInstance(application);
        accountDao = database.accountDao();
        transactionDao = database.transactionDao();
        allAccounts = accountDao.getAllAccounts();

    }

    private BankRepository(Context context) {
        BankDatabase database = Room.databaseBuilder(context, BankDatabase.class, "bank.db")
                .allowMainThreadQueries()
                .build();
    }


    public void addAccount(Account account) {
        long accountId = AccountDao.addAccount(account);
        account.setId(accountId);
    }

    public Account getAccount(long accountId) {
        return AccountDao.getAccount(accountId);
    }

    public List<Account> getAccounts() {
        return mAccountDao.getAccounts();
    }

    public void updateAccount(Account account) {
        mAccountDao.updateAccount(account);
    }

    public Transaction getTransaction(long transactionId) {
        return mTransactionDao.getTransaction(transactionId);
    }

    public List<Transaction> getTransactions(long accountId) {
        return mTransactionDao.getTransactions(accountId);
    }

    public void addTransaction(Transaction transaction) {
        long questionId = mTransactionDao.addTransaction(transaction);
        transaction.setId(questionId);
    }
}
