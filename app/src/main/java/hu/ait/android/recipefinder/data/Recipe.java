package hu.ait.android.recipefinder.data;

import java.io.Serializable;

import hu.ait.android.recipefinder.R;

public class Recipe implements Serializable {

    private String recipeName;

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
