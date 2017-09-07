package hu.ait.android.recipefinder.data;

import java.io.Serializable;


public class Recipe implements Serializable {

    private String recipeName;
    private String recipeImage;
    private int recipeId;
    private int missingIngredients;

    public Recipe(String recipeName, int recipeId, int missingIngredients, String recipeImage) {
        this.recipeName = recipeName;
        this.recipeId = recipeId;
        this.missingIngredients = missingIngredients;
        this.recipeImage = recipeImage;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getMissingIngredients() {
        return missingIngredients;
    }

    public void setMissingIngredients(int missingIngredients) {
        this.missingIngredients = missingIngredients;
    }
}
