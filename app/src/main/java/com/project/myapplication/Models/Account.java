package com.project.myapplication.Models;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;
    @NonNull
    @ColumnInfo(name = "firstName")
    private String firstName;
    @NonNull
    @ColumnInfo(name = "lastName")
    private String lastName;
    @NonNull
    @ColumnInfo(name = "username")
    private String username;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;
    @NonNull
    @ColumnInfo(name = "balance")
    private double balance;


    Account( String firstName, String lastName, String username, String password, double balance)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public long getId()
    {
        return mId;
    }

    public void setId(long id)
    {
        mId = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
