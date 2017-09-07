package hu.ait.android.recipefinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hu.ait.android.recipefinder.data.ExtendedIngredient;
import hu.ait.android.recipefinder.data.RecipeInfo;
import hu.ait.android.recipefinder.network.RecipeDetailsAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeDetailsActivity extends AppCompatActivity {
    /* Scrolling View activity for displaying the full content of one recipe after it is chosen. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btnBack = (FloatingActionButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               RecipeDetailsActivity.this.finish();
            }
        });

        final String apiKey = "UrWhOZ46CrmshFRa6xlDSUVTQINFp1TC45ZjsnpEskovgapLh0";

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(getString(R.string.base_url)).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final RecipeDetailsAPI recipeDetailsAPI = retrofit.create(RecipeDetailsAPI.class);


        Bundle extra = getIntent().getExtras();
        final int recipeID = (extra.getInt("RECIPE_ID"));

        //getRecipeDetails(int id, boolean includeNutrition, and apiKey)
        Call<RecipeInfo> recipeDetailsResultCall = recipeDetailsAPI.getRecipeDetails(
                recipeID,
                false,
                apiKey
        );

        //Add information to the details ScrollerView.
        recipeDetailsResultCall.enqueue(new Callback<RecipeInfo>() {
            @Override
            public void onResponse(Call<RecipeInfo> call, Response<RecipeInfo> response) {
                Toast.makeText(RecipeDetailsActivity.this, "API call ok", Toast.LENGTH_SHORT).show();
                //Fill layout with information.

                TextView tvRecipeTitle = (TextView) findViewById(R.id.tvRecipeTitle);
                tvRecipeTitle.setText("" + response.body().getTitle());

                TextView tvReadyTime = (TextView) findViewById(R.id.tvReadyTime);
                tvReadyTime.setText("Ready in " + response.body().getReadyInMinutes() + " minutes.");

                TextView tvRecipeServings = (TextView) findViewById(R.id.tvRecipeServings);
                tvRecipeServings.setText("Servings: " + response.body().getServings());

//                TextView tvExtendedIngredients = (TextView) findViewById(R.id.tvExtendedIngredients);
//                tvExtendedIngredients.setText("" + response.body().getExtendedIngredients());
            }

            @Override
            public void onFailure(Call<RecipeInfo> call, Throwable t) {
                Toast.makeText(RecipeDetailsActivity.this, "Error in api call: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
