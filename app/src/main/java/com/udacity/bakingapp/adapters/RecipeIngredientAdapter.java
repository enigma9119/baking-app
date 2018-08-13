package com.udacity.bakingapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.bakingapp.R;
import com.udacity.bakingapp.model.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeIngredientAdapter extends RecyclerView.Adapter<RecipeIngredientAdapter.RecipeIngredientViewHolder> {

    private final ArrayList<Ingredient> mIngredients;

    public RecipeIngredientAdapter(ArrayList<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    @NonNull
    @Override
    public RecipeIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipestep_list_ingredients, parent, false);
        return new RecipeIngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeIngredientViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);

        holder.mQuantity.setText(String.valueOf(ingredient.getQuantity()));
        holder.mMeasure.setText(ingredient.getMeasure());
        holder.mIngredientName.setText(ingredient.getName());
    }

    @Override
    public int getItemCount() {
        if(mIngredients == null) return 0;
        return mIngredients.size();
    }

    class RecipeIngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quantity) TextView mQuantity;
        @BindView(R.id.measure) TextView mMeasure;
        @BindView(R.id.ingredient_name) TextView mIngredientName;

        RecipeIngredientViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}