package com.project.myapplication.Repos;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.project.myapplication.Models.Account;
import java.util.List;

@Dao
public interface AccountDao {
    @Insert
    void insert(Account account);

    // below method is use to update
    // the data in our database.
    @Update
    void update(Account account);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(Account account);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM Account")
    void deleteAllCourses();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM Account ORDER BY accountId ASC")
    LiveData<List<Account>> getAllAccounts();


}
