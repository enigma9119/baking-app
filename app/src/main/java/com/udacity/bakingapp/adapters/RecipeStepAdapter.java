package com.udacity.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.bakingapp.R;
import com.udacity.bakingapp.RecipeStepDetailActivity;
import com.udacity.bakingapp.RecipeStepDetailFragment;
import com.udacity.bakingapp.RecipeStepListActivity;
import com.udacity.bakingapp.model.RecipeStep;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder> {

    private final RecipeStepListActivity mParentActivity;
    private final ArrayList<RecipeStep> mRecipeSteps;
    private final String mRecipeName;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecipeStep step = (RecipeStep) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(RecipeStepDetailFragment.ARG_RECIPE_STEP, Parcels.wrap(step));

                RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
                fragment.setArguments(arguments);

                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipestep_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, RecipeStepDetailActivity.class);
                intent.putExtra(RecipeStepDetailFragment.ARG_RECIPE_STEP, Parcels.wrap(step));
                intent.putExtra(RecipeStepDetailActivity.EXTRA_RECIPE_NAME, mRecipeName);

                context.startActivity(intent);
            }
        }
    };

    public RecipeStepAdapter(RecipeStepListActivity parent,
                             ArrayList<RecipeStep> recipeSteps,
                             String recipeName,
                             boolean twoPane) {
        mRecipeSteps = recipeSteps;
        mRecipeName = recipeName;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @NonNull
    @Override
    public RecipeStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipestep_list_content, parent, false);
        return new RecipeStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeStepViewHolder holder, int position) {
        RecipeStep step = mRecipeSteps.get(position);

        holder.mRecipeId.setText(String.valueOf(step.getRecipeStepId()));
        holder.mShortDescription.setText(step.getShortDescription());

        holder.itemView.setTag(step);
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        if(mRecipeSteps == null) return 0;
        return mRecipeSteps.size();
    }

    class RecipeStepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipe_step_id) TextView mRecipeId;
        @BindView(R.id.short_description) TextView mShortDescription;

        RecipeStepViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}