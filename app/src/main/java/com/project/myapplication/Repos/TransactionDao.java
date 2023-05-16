package com.project.myapplication.Repos;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.project.myapplication.Models.Transaction;
import java.util.List;

@Dao
public interface TransactionDao
{
    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    LiveData<Transaction> getTransaction(long id);

    @Query("SELECT * FROM `Transaction` WHERE accountId = :accountId ORDER BY id")
    LiveData<List<Transaction>> getTransactions(long accountId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addTransaction(Transaction transaction);

}
