package com.udacity.bakingapp.model;

import org.parceler.Parcel;

@Parcel
public class RecipeStep {

    long recipeStepId;
    String shortDescription;
    String description;
    String videoUrl;
    String thumbnailUrl;

    public long getRecipeStepId() {
        return recipeStepId;
    }

    public void setRecipeStepId(long recipeStepId) {
        this.recipeStepId = recipeStepId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
