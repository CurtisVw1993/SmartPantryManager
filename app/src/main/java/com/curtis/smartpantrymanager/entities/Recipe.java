package com.curtis.smartpantrymanager.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String recipeName;
    private String instructions;

    public Recipe(String recipeName,
                  String instructions) {
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}