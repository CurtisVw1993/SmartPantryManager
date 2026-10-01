package com.curtis.smartpantrymanager.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.curtis.smartpantrymanager.entities.RecipeIngredient;

import java.util.List;

@Dao
public interface RecipeIngredientDao {

    @Insert
    void insert(RecipeIngredient ingredient);

    @Query("SELECT * FROM recipe_ingredients WHERE recipeId = :recipeId")
    List<RecipeIngredient> getRecipeIngredients(int recipeId);
}