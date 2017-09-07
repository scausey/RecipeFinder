package hu.ait.android.recipefinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import hu.ait.android.recipefinder.adapter.RecipeAdapter;
import hu.ait.android.recipefinder.data.Recipe;
import hu.ait.android.recipefinder.data.RecipeList;
import hu.ait.android.recipefinder.network.RecipeAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeListActivity extends AppCompatActivity {
    /* Displayed recipes based on entered ingredients. */

    private RecipeAdapter recipeAdapter = new RecipeAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btnBack = (FloatingActionButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeListActivity.this.finish();
            }
        });

        final String apiKey = "UrWhOZ46CrmshFRa6xlDSUVTQINFp1TC45ZjsnpEskovgapLh0";

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);

        //Get ingredient input from MainActivity.
        Intent intentShowRecipeResult = getIntent();
        final String ingredients = intentShowRecipeResult.getStringExtra(MainActivity.KEY_INGREDIENT);

        //getRecipes(boolean fillIngredients, String ingredients, boolean limitLicense, int number, int ranking)
        Call<ArrayList<RecipeList>> recipeResultCall = recipeAPI.getRecipes(
                false,
                ingredients,
                true,
                6,
                1,
                apiKey
        );
        //Create a recipe card for each recipe returned.
        recipeResultCall.enqueue(new Callback<ArrayList<RecipeList>>() {
            @Override
            public void onResponse(Call<ArrayList<RecipeList>> call, Response<ArrayList<RecipeList>> response) {
                Toast.makeText(RecipeListActivity.this, "API call success"+response.body().size(), Toast.LENGTH_SHORT).show();

                for (RecipeList newRecipe : response.body()) {
                    recipeAdapter.addRecipe(new Recipe(newRecipe.getTitle(),
                            newRecipe.getRecipeID(),
                            newRecipe.getMissedIngredientCount(),
                            newRecipe.getImage()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecipeList>> call, Throwable t) {
                Toast.makeText(RecipeListActivity.this, "Error in api call: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // RecyclerView layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);
    }
}
