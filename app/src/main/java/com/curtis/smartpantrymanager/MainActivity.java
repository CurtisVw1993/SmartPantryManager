package com.curtis.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.curtis.smartpantrymanager.activities.AddIngredientActivity;
import com.curtis.smartpantrymanager.adapters.IngredientAdapter;
import com.curtis.smartpantrymanager.database.AppDatabase;
import com.curtis.smartpantrymanager.entities.Ingredient;
import com.curtis.smartpantrymanager.activities.RecipesActivity;

import com.curtis.smartpantrymanager.activities.SettingsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =
                findViewById(R.id.recyclerIngredients);

        Button btnAddIngredient =
                findViewById(R.id.btnAddIngredient);

        Button btnSettings =
                findViewById(R.id.btnSettings);

        btnSettings.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class);

            startActivity(intent);

        });

        btnAddIngredient.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AddIngredientActivity.class);

            startActivity(intent);

        });

        Button btnRecipes =
                findViewById(R.id.btnRecipes);

        btnRecipes.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            RecipesActivity.class);

            startActivity(intent);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppDatabase db =
                AppDatabase.getInstance(this);

        List<Ingredient> ingredients =
                db.ingredientDao()
                        .getAllIngredients();

        IngredientAdapter adapter =
                new IngredientAdapter(
                        this,
                        ingredients);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
}