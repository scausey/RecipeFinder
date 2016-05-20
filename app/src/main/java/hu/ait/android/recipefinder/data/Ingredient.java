package hu.ait.android.recipefinder.data;

import com.orm.SugarRecord;
import java.io.Serializable;

/**
 * Created by User on 5/20/2016.
 */
public class Ingredient extends SugarRecord<Ingredient> implements Serializable {
    private String ingredientNames;

    public Ingredient() {

    }

    public Ingredient (String ingredientNames) {
        this.ingredientNames = ingredientNames;
    }

    public String getIngredientNames() {
        return ingredientNames;
    }

    public void setIngredientNames(String ingredientNames) {
        this.ingredientNames = ingredientNames;
    }

}
