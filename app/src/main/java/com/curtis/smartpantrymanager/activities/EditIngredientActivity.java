package com.curtis.smartpantrymanager.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.curtis.smartpantrymanager.R;
import com.curtis.smartpantrymanager.database.AppDatabase;
import com.curtis.smartpantrymanager.entities.Ingredient;

public class EditIngredientActivity extends AppCompatActivity {

    private EditText etIngredientName;
    private EditText etQuantity;
    private EditText etUnit;
    private EditText etExpiryDate;

    private Ingredient ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        etIngredientName =
                findViewById(R.id.etIngredientName);

        etQuantity =
                findViewById(R.id.etQuantity);

        etUnit =
                findViewById(R.id.etUnit);

        etExpiryDate =
                findViewById(R.id.etExpiryDate);

        Button btnSave =
                findViewById(R.id.btnSave);

        btnSave.setText("Update Ingredient");

        int ingredientId =
                getIntent().getIntExtra(
                        "ingredientId",
                        -1);

        AppDatabase db =
                AppDatabase.getInstance(this);

        ingredient =
                db.ingredientDao()
                        .getIngredientById(
                                ingredientId);

        if (ingredient != null) {

            etIngredientName.setText(
                    ingredient.getName());

            etQuantity.setText(
                    String.valueOf(
                            ingredient.getQuantity()));

            etUnit.setText(
                    ingredient.getUnit());

            etExpiryDate.setText(
                    ingredient.getExpiryDate());
        }

        btnSave.setOnClickListener(v -> {

            ingredient.setName(
                    etIngredientName
                            .getText()
                            .toString()
                            .trim());

            ingredient.setQuantity(
                    Double.parseDouble(
                            etQuantity
                                    .getText()
                                    .toString()));

            ingredient.setUnit(
                    etUnit
                            .getText()
                            .toString()
                            .trim());

            ingredient.setExpiryDate(
                    etExpiryDate
                            .getText()
                            .toString()
                            .trim());

            db.ingredientDao()
                    .update(ingredient);

            Toast.makeText(
                    this,
                    "Ingredient Updated",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        });
        }
    }