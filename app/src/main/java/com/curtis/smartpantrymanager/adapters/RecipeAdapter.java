package com.curtis.smartpantrymanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curtis.smartpantrymanager.R;
import com.curtis.smartpantrymanager.entities.Recipe;

import android.content.Intent;

import com.curtis.smartpantrymanager.activities.RecipeDetailActivity;

import java.util.List;

public class RecipeAdapter
        extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipes;

    public RecipeAdapter(
            android.content.Context context,
            List<Recipe> recipes) {

        this.context = context;
        this.recipes = recipes;
    }

    private android.content.Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.row_recipe,
                                parent,
                                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Recipe recipe =
                recipes.get(position);

        holder.txtRecipeName.setText(
                recipe.getRecipeName());

        holder.itemView.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            context,
                            RecipeDetailActivity.class);

            intent.putExtra(
                    "recipeName",
                    recipe.getRecipeName());

            intent.putExtra(
                    "ingredients",
                    "See pantry requirements for this recipe");

            intent.putExtra(
                    "instructions",
                    recipe.getInstructions());

            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtRecipeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRecipeName =
                    itemView.findViewById(
                            R.id.txtRecipeName);
        }
    }
}