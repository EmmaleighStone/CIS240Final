package com.project.myapplication.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Account.class, parentColumns = "id",
        childColumns = "accountId", onDelete = CASCADE))
public class Transaction
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "accountId")
    private long accountId;

    Transaction (double amount, String type, String date, long accountId)
    {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.accountId = accountId;
    }

    public long getId()
    {
        return mId;
    }

    public void setId(long id)
    {
        mId = id;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {

        this.date = date;
    }

    public long getAccountId()
    {
        return accountId;
    }

    public void setAccountId(long accountId)
    {
        this.accountId = accountId;
    }
}
