package hu.ait.android.recipefinder.network;

import hu.ait.android.recipefinder.data.RecipeInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeDetailsAPI {
    @GET("recipes/{id}/information")
    Call<RecipeInfo> getRecipeDetails(@Path("id") int id,
                                      @Query("includeNutrition") boolean includeNutrition,
                                      @Header("X-Mashape-Key") String key
    );
}
