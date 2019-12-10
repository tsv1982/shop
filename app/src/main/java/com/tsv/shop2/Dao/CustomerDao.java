package com.tsv.shop2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.tsv.shop2.entity.Customer1;

import java.util.List;

@Dao
public interface CustomerDao {
    @Query("SELECT * FROM customer1")
    List<Customer1> getAll();

    @Query("SELECT * FROM Customer1 WHERE IdCus = :id")
    Customer1 getById(int id);

    @Insert
    void insert(Customer1 customer1);

    @Update
    void updare(Customer1 customer1);

    @Delete
    void delete(Customer1 customer1);



}
