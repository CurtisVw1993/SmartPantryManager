package com.curtis.smartpantrymanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.curtis.smartpantrymanager.R;
import com.curtis.smartpantrymanager.adapters.RecipeAdapter;
import com.curtis.smartpantrymanager.database.AppDatabase;
import com.curtis.smartpantrymanager.entities.Recipe;
import com.curtis.smartpantrymanager.entities.RecipeIngredient;
import com.curtis.smartpantrymanager.entities.Ingredient;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        recyclerRecipes = findViewById(R.id.recyclerRecipes);

        AppDatabase db = AppDatabase.getInstance(this);

        seedRecipes(db);

        List<Recipe> allRecipes =
                db.recipeDao().getAllRecipes();

        List<Recipe> matchingRecipes =
                new ArrayList<>();

        TextView txtNoRecipes =
                findViewById(R.id.txtNoRecipes);

        if (matchingRecipes.isEmpty()) {

            txtNoRecipes.setVisibility(
                    View.VISIBLE);
        }

        for (Recipe recipe : allRecipes) {

            if (canMakeRecipe(
                    recipe.getId(),
                    db)) {

                matchingRecipes.add(recipe);
            }
        }

        RecipeAdapter adapter =
                new RecipeAdapter(
                        this,
                        matchingRecipes);

        recyclerRecipes.setLayoutManager(
                new LinearLayoutManager(this));

        recyclerRecipes.setAdapter(adapter);
    }

    private boolean canMakeRecipe(
            int recipeId,
            AppDatabase db) {

        List<Ingredient> pantryItems =
                db.ingredientDao()
                        .getAllIngredients();

        List<RecipeIngredient> recipeItems =
                db.recipeIngredientDao()
                        .getRecipeIngredients(recipeId);

        for (RecipeIngredient recipeItem : recipeItems) {

            boolean found = false;

            for (Ingredient pantryItem : pantryItems) {

                if (pantryItem.getName()
                        .equalsIgnoreCase(
                                recipeItem.getIngredientName())) {

                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }

    private void seedRecipes(AppDatabase db) {

        if (db.recipeDao().getAllRecipes().size() > 0) {
            return;
        }

        long omeletteId =
                db.recipeDao().insert(
                        new Recipe(
                                "Cheese Omelette",
                                "Beat eggs and cheese together and fry."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) omeletteId, "Eggs"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) omeletteId, "Milk"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) omeletteId, "Cheese"));


        long toastId =
                db.recipeDao().insert(
                        new Recipe(
                                "French Toast",
                                "Dip bread in egg mixture and fry."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) toastId, "Bread"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) toastId, "Eggs"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) toastId, "Milk"));


        long saladId =
                db.recipeDao().insert(
                        new Recipe(
                                "Tomato Salad",
                                "Mix tomato and onion together."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) saladId, "Tomato"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) saladId, "Onion"));


        long sandwichId =
                db.recipeDao().insert(
                        new Recipe(
                                "Cheese Sandwich",
                                "Place cheese between bread slices."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) sandwichId, "Bread"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) sandwichId, "Cheese"));


        long scrambledEggsId =
                db.recipeDao().insert(
                        new Recipe(
                                "Scrambled Eggs",
                                "Whisk eggs and cook gently."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) scrambledEggsId, "Eggs"));


        long tomatoSoupId =
                db.recipeDao().insert(
                        new Recipe(
                                "Tomato Soup",
                                "Boil tomatoes and blend until smooth."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) tomatoSoupId, "Tomato"));


        long pancakesId =
                db.recipeDao().insert(
                        new Recipe(
                                "Pancakes",
                                "Mix ingredients and fry small portions."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) pancakesId, "Eggs"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) pancakesId, "Milk"));


        long fruitSmoothieId =
                db.recipeDao().insert(
                        new Recipe(
                                "Fruit Smoothie",
                                "Blend fruit and milk together."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) fruitSmoothieId, "Milk"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) fruitSmoothieId, "Banana"));


        long riceBowlId =
                db.recipeDao().insert(
                        new Recipe(
                                "Rice Bowl",
                                "Cook rice and serve."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) riceBowlId, "Rice"));


        long friedRiceId =
                db.recipeDao().insert(
                        new Recipe(
                                "Egg Fried Rice",
                                "Mix cooked rice and eggs in a pan."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) friedRiceId, "Rice"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) friedRiceId, "Eggs"));


        long chickenPastaId =
                db.recipeDao().insert(
                        new Recipe(
                                "Chicken Pasta",
                                "Cook pasta and chicken together."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) chickenPastaId, "Chicken"));
        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) chickenPastaId, "Pasta"));


        long chickenCurryId =
                db.recipeDao().insert(
                        new Recipe(
                                "Chicken Curry",
                                "Cook chicken in curry sauce."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) chickenCurryId, "Chicken"));

        long bananaShakeId =
                db.recipeDao().insert(
                        new Recipe(
                                "Banana Milkshake",
                                "Blend banana and milk together."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) bananaShakeId, "Banana"));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) bananaShakeId, "Milk"));

        long eggSandwichId =
                db.recipeDao().insert(
                        new Recipe(
                                "Egg Sandwich",
                                "Place cooked egg between bread slices."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) eggSandwichId, "Eggs"));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) eggSandwichId, "Bread"));

        long cheeseRiceId =
                db.recipeDao().insert(
                        new Recipe(
                                "Cheese Rice",
                                "Mix cooked rice with cheese."
                        ));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) cheeseRiceId, "Rice"));

        db.recipeIngredientDao().insert(
                new RecipeIngredient((int) cheeseRiceId, "Cheese"));
    }
}