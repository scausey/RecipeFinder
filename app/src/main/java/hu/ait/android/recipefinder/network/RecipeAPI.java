package hu.ait.android.recipefinder.network;

import java.util.ArrayList;

import hu.ait.android.recipefinder.data.RecipeList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RecipeAPI {
    @GET("recipes/findByIngredients")
    Call<ArrayList<RecipeList>> getRecipes(@Query("fillIngredients") boolean fillIngredients,
                                             @Query("ingredients") String ingredients,
                                             @Query("limitLicense") boolean limitLicense,
                                             @Query("number") int number,
                                             @Query("ranking") int ranking,
                                             @Header("X-Mashape-Key") String key
    );
//https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients=apples%2Cflour%2Csugar&limitLicense=false&number=5&ranking=1' \
}
