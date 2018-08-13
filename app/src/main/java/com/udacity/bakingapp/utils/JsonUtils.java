package com.udacity.bakingapp.utils;

import android.util.Log;

import com.udacity.bakingapp.model.Ingredient;
import com.udacity.bakingapp.model.Recipe;
import com.udacity.bakingapp.model.RecipeStep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<Recipe> parseAllRecipesJson(String json) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            JSONArray allRecipesData = new JSONArray(json);
            for (int i = 0; i < allRecipesData.length(); i++) {
                recipes.add(parseRecipeJson(allRecipesData.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    private static Recipe parseRecipeJson(JSONObject recipeData) {
        Recipe recipe = new Recipe();

        try {
            recipe.setRecipeId(recipeData.getInt("id"));
            recipe.setName(recipeData.getString("name"));
            recipe.setServings(recipeData.getInt("servings"));
            recipe.setImageUrl(recipeData.getString("image"));

            // Set the ingredients data in the recipe object

            JSONArray ingredientsData = recipeData.getJSONArray("ingredients");
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsData.length(); i++) {
                Ingredient ingredient = new Ingredient();
                JSONObject ingredientData = ingredientsData.getJSONObject(i);

                ingredient.setName(ingredientData.getString("ingredient"));
                ingredient.setQuantity(ingredientData.getInt("quantity"));
                ingredient.setMeasure(ingredientData.getString("measure"));

                ingredients.add(ingredient);
            }
            recipe.setIngredients(ingredients);

            // Set the recipe steps data in the recipe object

            JSONArray recipeStepsData = recipeData.getJSONArray("steps");
            ArrayList<RecipeStep> recipeSteps = new ArrayList<>();

            for (int i = 0; i < recipeStepsData.length(); i++) {
                RecipeStep recipeStep = new RecipeStep();
                JSONObject recipeStepData = recipeStepsData.getJSONObject(i);

                recipeStep.setRecipeStepId(recipeStepData.getInt("id"));
                recipeStep.setShortDescription(recipeStepData.getString("shortDescription"));
                recipeStep.setDescription(recipeStepData.getString("description"));
                recipeStep.setVideoUrl(recipeStepData.getString("videoURL"));
                recipeStep.setThumbnailUrl(recipeStepData.getString("thumbnailURL"));

                recipeSteps.add(recipeStep);
            }
            recipe.setRecipeSteps(recipeSteps);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipe;
    }
}
