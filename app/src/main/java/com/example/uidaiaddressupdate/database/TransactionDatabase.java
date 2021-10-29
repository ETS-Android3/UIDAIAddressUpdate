package com.example.uidaiaddressupdate.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LandlordTransactions.class,RenterTransactions.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    private static TransactionDatabase instance;

    public abstract LandlordTransactionsDao landlordTransactionsDao();
    public abstract RenterTransactionsDao renterTransactionsDao();

    public static synchronized TransactionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TransactionDatabase.class, "transaction_database")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}
