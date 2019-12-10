package com.tsv.shop2.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tsv.shop2.entity.ShopEntity1;

import java.util.List;

@Dao
public interface ShopEntityDao {
    @Query("SELECT * FROM ShopEntity1")
    List<ShopEntity1> getAll();

    @Query("SELECT * FROM ShopEntity1 WHERE id = :id")
    ShopEntity1 getById(long id);

    @Insert
    void insert(ShopEntity1 shopEntity1);

    @Update
    void update(ShopEntity1 shopEntity1);

    @Delete
    void delete(ShopEntity1 shopEntity1);
}
