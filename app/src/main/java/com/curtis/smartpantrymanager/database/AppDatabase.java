package com.curtis.smartpantrymanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.curtis.smartpantrymanager.dao.IngredientDao;
import com.curtis.smartpantrymanager.dao.RecipeDao;
import com.curtis.smartpantrymanager.dao.RecipeIngredientDao;
import com.curtis.smartpantrymanager.entities.Ingredient;
import com.curtis.smartpantrymanager.entities.Recipe;
import com.curtis.smartpantrymanager.entities.RecipeIngredient;

@Database(
        entities = {
                Ingredient.class,
                Recipe.class,
                RecipeIngredient.class
        },
        version = 4,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract IngredientDao ingredientDao();

    public abstract RecipeDao recipeDao();

    public abstract RecipeIngredientDao recipeIngredientDao();

    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "smart_pantry_db"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}