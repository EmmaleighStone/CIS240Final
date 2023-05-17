package com.project.myapplication.Models;

import java.util.ArrayList;

public class Account {
    public int accountId;
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public Double balance;

    public ArrayList<Transaction> transactions;

    public Account()
    {

    }

    public Account(String firstName, String lastName, String username, String password, Double balance)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>();
    }



}