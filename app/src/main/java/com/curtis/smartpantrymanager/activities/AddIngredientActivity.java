package com.curtis.smartpantrymanager.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.curtis.smartpantrymanager.R;
import com.curtis.smartpantrymanager.database.AppDatabase;
import com.curtis.smartpantrymanager.entities.Ingredient;

public class AddIngredientActivity extends AppCompatActivity {

    private EditText etIngredientName;
    private EditText etQuantity;
    private EditText etUnit;
    private EditText etExpiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        etIngredientName = findViewById(R.id.etIngredientName);
        etQuantity = findViewById(R.id.etQuantity);
        etUnit = findViewById(R.id.etUnit);
        etExpiryDate = findViewById(R.id.etExpiryDate);

        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveIngredient());
    }

    private void saveIngredient() {

        String name = etIngredientName.getText().toString().trim();
        String quantityText = etQuantity.getText().toString().trim();
        String unit = etUnit.getText().toString().trim();
        String expiryDate = etExpiryDate.getText().toString().trim();

        if (name.isEmpty()) {
            etIngredientName.setError("Ingredient name required");
            return;
        }

        if (quantityText.isEmpty()) {
            etQuantity.setError("Quantity required");
            return;
        }

        double quantity = Double.parseDouble(quantityText);

        Ingredient ingredient =
                new Ingredient(
                        name,
                        quantity,
                        unit,
                        expiryDate
                );

        AppDatabase db =
                AppDatabase.getInstance(this);

        db.ingredientDao().insert(ingredient);

        Toast.makeText(
                this,
                "Ingredient Saved",
                Toast.LENGTH_SHORT
        ).show();

        finish();
    }
}