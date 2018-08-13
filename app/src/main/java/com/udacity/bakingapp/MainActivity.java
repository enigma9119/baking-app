package com.udacity.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.udacity.bakingapp.adapters.RecipeAdapter;
import com.udacity.bakingapp.model.Recipe;
import com.udacity.bakingapp.utils.JsonUtils;

import org.parceler.Parcels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ListItemClickListener {

    public static final String RECIPE_EXTRA = "recipe_extra";

    @BindView(R.id.recipe_recycler_view)
    RecyclerView mRecipeRecyclerView;

    private RecipeAdapter mRecipeAdapter;
    private ArrayList<Recipe> mBakingRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecipeRecyclerView.setHasFixedSize(true);

        mBakingRecipes = getAllBakingRecipes();
        mRecipeAdapter = new RecipeAdapter(mBakingRecipes, this);
        mRecipeRecyclerView.setAdapter(mRecipeAdapter);
    }

    private ArrayList<Recipe> getAllBakingRecipes() {
        InputStream is = getResources().openRawResource(R.raw.baking);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = writer.toString();

        return JsonUtils.parseAllRecipesJson(jsonString);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Recipe recipe = mBakingRecipes.get(clickedItemIndex);

        Intent intent = new Intent(this, RecipeStepListActivity.class);
        intent.putExtra(RECIPE_EXTRA, Parcels.wrap(recipe));
        startActivity(intent);
    }
}
