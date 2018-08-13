package com.udacity.bakingapp;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.bakingapp.model.RecipeStep;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a single RecipeStep detail screen.
 * This fragment is either contained in a {@link RecipeStepListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeStepDetailActivity}
 * on handsets.
 */
public class RecipeStepDetailFragment extends Fragment {

    public static final String ARG_RECIPE_STEP = "recipe_step_arg";

    @BindView(R.id.description) TextView mDescription;

    private RecipeStep mRecipeStep;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeStepDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        if (getArguments().containsKey(ARG_RECIPE_STEP)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mRecipeStep = Parcels.unwrap(getArguments().getParcelable(ARG_RECIPE_STEP));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipestep_detail, container, false);
        ButterKnife.bind(this, rootView);

        if (mRecipeStep != null) {
            mDescription.setText(mRecipeStep.getDescription());
        }

        return rootView;
    }
}
