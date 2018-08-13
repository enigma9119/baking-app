package com.udacity.bakingapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.bakingapp.R;
import com.udacity.bakingapp.model.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> mRecipes;
    private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public RecipeAdapter(ArrayList<Recipe> recipes, ListItemClickListener listener) {
        mRecipes = recipes;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);

        holder.mRecipeName.setText(recipe.getName());
        Picasso.get()
                .load(recipe.getImageUrl())
                .resize(1200, 530)
                .centerCrop()
                .into(holder.mRecipeImage);
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) return 0;
        return mRecipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.recipe_image)
        ImageView mRecipeImage;
        @BindView(R.id.recipe_name)
        TextView mRecipeName;

        RecipeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
