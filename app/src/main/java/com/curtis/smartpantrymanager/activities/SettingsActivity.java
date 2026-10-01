package com.curtis.smartpantrymanager.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.curtis.smartpantrymanager.R;
import com.curtis.smartpantrymanager.database.AppDatabase;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnClearPantry =
                findViewById(R.id.btnClearPantry);

        Button btnAbout =
                findViewById(R.id.btnAbout);

        btnClearPantry.setOnClickListener(v -> {

            new AlertDialog.Builder(this)
                    .setTitle("Clear Pantry")
                    .setMessage("Delete all ingredients?")
                    .setPositiveButton("Yes",
                            (dialog, which) -> {

                                AppDatabase db =
                                        AppDatabase.getInstance(this);

                                db.ingredientDao()
                                        .deleteAllIngredients();

                                Toast.makeText(
                                        this,
                                        "Pantry Cleared",
                                        Toast.LENGTH_SHORT
                                ).show();

                            })
                    .setNegativeButton(
                            "No",
                            null)
                    .show();

        });

        btnAbout.setOnClickListener(v -> {

            new AlertDialog.Builder(this)
                    .setTitle("About")
                    .setMessage(
                            "Smart Pantry Manager\n\nVersion 1.0\n\nDeveloped for Mobile App Development 700 Assignment")
                    .show();

        });
    }
}