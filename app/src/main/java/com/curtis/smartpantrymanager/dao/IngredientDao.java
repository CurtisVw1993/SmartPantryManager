package com.curtis.smartpantrymanager.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.curtis.smartpantrymanager.entities.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {

    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("SELECT * FROM ingredients")
    List<Ingredient> getAllIngredients();

    @Query("SELECT * FROM ingredients WHERE id = :id")
    Ingredient getIngredientById(int id);
    @Query("DELETE FROM ingredients")
    void deleteAllIngredients();
}