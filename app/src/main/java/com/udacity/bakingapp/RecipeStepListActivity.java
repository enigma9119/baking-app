package com.udacity.bakingapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.udacity.bakingapp.adapters.RecipeIngredientAdapter;
import com.udacity.bakingapp.adapters.RecipeStepAdapter;
import com.udacity.bakingapp.model.Recipe;

import org.parceler.Parcels;

/**
 * An activity representing a list of RecipeSteps. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeStepDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeStepListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipestep_list);

        if (getIntent().hasExtra(MainActivity.RECIPE_EXTRA)) {

            // Get the recipe that will be displayed in this activity
            mRecipe = Parcels.unwrap(getIntent().getParcelableExtra(MainActivity.RECIPE_EXTRA));

            final CollapsingToolbarLayout appBarLayout = findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mRecipe.getName());

                Picasso.get()
                        .load(mRecipe.getImageUrl())
                        .resize(1200, 530)
                        .centerCrop()
                        .into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                // Load the recipe image into the collapsing toolbar
                                appBarLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
                            }

                            @Override
                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });
            }
        }

        // Display the recipe name on the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(mRecipe.getName());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.recipestep_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        RecyclerView ingredientsRecyclerView = findViewById(R.id.recipe_ingredient_list);
        ingredientsRecyclerView.setAdapter(new RecipeIngredientAdapter(mRecipe.getIngredients()));

        RecyclerView stepsRecyclerView = findViewById(R.id.recipe_step_list);
        stepsRecyclerView.setAdapter(new RecipeStepAdapter(this, mRecipe.getRecipeSteps(), mTwoPane));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
