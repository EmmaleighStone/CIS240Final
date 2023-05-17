package com.project.myapplication.Models;

public class Transaction
{
    public int accountId;
    public int transactionId;
    public double amount;
    public String type;
    public String date;

    public Transaction(int accountId, double amount, String type, String date)
    {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

}