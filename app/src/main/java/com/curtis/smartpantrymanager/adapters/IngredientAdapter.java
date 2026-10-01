package com.curtis.smartpantrymanager.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curtis.smartpantrymanager.R;
import com.curtis.smartpantrymanager.database.AppDatabase;
import com.curtis.smartpantrymanager.entities.Ingredient;

import java.util.List;

import android.content.Intent;

import com.curtis.smartpantrymanager.activities.EditIngredientActivity;

public class IngredientAdapter
        extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private List<Ingredient> ingredients;
    private Context context;

    public IngredientAdapter(Context context,
                             List<Ingredient> ingredients) {

        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.row_ingredient,
                                parent,
                                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Ingredient ingredient =
                ingredients.get(position);

        holder.txtIngredient.setText(
                ingredient.getName()
                        + "\nQuantity: "
                        + ingredient.getQuantity()
                        + " "
                        + ingredient.getUnit()
                        + "\nExpiry: "
                        + ingredient.getExpiryDate());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    context,
                    EditIngredientActivity.class
            );

            intent.putExtra(
                    "ingredientId",
                    ingredient.getId()
            );

            context.startActivity(intent);

        });

        holder.itemView.setOnLongClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Delete Ingredient")
                    .setMessage("Delete this ingredient?")
                    .setPositiveButton("Yes", (dialog, which) -> {

                        AppDatabase db =
                                AppDatabase.getInstance(context);

                        db.ingredientDao().delete(ingredient);

                        ingredients.remove(position);

                        notifyDataSetChanged();

                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtIngredient;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            txtIngredient =
                    itemView.findViewById(
                            R.id.txtIngredient);
        }
    }
}