package com.udacity.bakingapp.model;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Recipe {

    long recipeId;
    String name;
    ArrayList<Ingredient> ingredients;
    ArrayList<RecipeStep> recipeSteps;
    int servings;
    String imageUrl;

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(ArrayList<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
