package com.example.uidaiaddressupdate.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LenderTransactionsDao {

    @Query("SELECT * FROM LenderTransactions")
    List<LenderTransactions> getTransactionList();

    @Query("SELECT * FROM LenderTransactions WHERE LenderTransactions.transactionID = :transactionId")
    LenderTransactions getTransaction(String transactionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTransaction(LenderTransactions lenderTransactions);

    @Query("DELETE FROM LenderTransactions WHERE LenderTransactions.transactionID = :transactionId")
    void deleteTransaction(String transactionId);
}
