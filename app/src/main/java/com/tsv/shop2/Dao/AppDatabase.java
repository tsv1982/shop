package com.tsv.shop2.Dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tsv.shop2.entity.Customer1;
import com.tsv.shop2.entity.ShopEntity1;

@Database(entities = {ShopEntity1.class, Customer1.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShopEntityDao shopEntityDao();

    public abstract CustomerDao customerDao();
}
