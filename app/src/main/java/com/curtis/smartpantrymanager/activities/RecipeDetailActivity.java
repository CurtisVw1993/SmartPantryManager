package com.curtis.smartpantrymanager.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.curtis.smartpantrymanager.R;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView txtRecipeName =
                findViewById(R.id.txtRecipeName);

        TextView txtIngredients =
                findViewById(R.id.txtIngredients);

        TextView txtInstructions =
                findViewById(R.id.txtInstructions);

        String recipeName =
                getIntent().getStringExtra("recipeName");

        String ingredients =
                getIntent().getStringExtra("ingredients");

        String instructions =
                getIntent().getStringExtra("instructions");

        txtRecipeName.setText(recipeName);
        txtIngredients.setText(ingredients);
        txtInstructions.setText(instructions);
    }
}