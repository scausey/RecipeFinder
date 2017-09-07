package hu.ait.android.recipefinder;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import hu.ait.android.recipefinder.data.Ingredient;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_INGREDIENT = "KEY_INGREDIENT";
    private LinearLayout layoutContent;
    private EditText etIngredients;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);
        etIngredients = (EditText) findViewById(R.id.etIngredients);

        Button btnRecipe = (Button) findViewById(R.id.btnRecipe);
        btnRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecipeResults(etIngredients.getText().toString());
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.action_help:
                                showSnackBarMessage(getString(R.string.help));
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_about:
                                Toast.makeText(MainActivity.this, "This App was created by Deana Bui and Louise Causey", Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                        }
                        return false;
                    }
                });
        setUpToolBar();
    }

    //Starts new intent to see recipe search results.
    public void showRecipeResults(String ingredientsText) {
        if (!"".equals (etIngredients.getText().toString())) {
            Intent intentShowRecipeResult = new Intent(
                    MainActivity.this,
                    RecipeListActivity.class);
            intentShowRecipeResult.putExtra(KEY_INGREDIENT, ingredientsText);
            startActivity(intentShowRecipeResult);
        } else {
            etIngredients.setError("This field is required!");
        }
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void showSnackBarMessage(String message) {
        Snackbar.make(layoutContent, message, Snackbar.LENGTH_LONG).setAction(R.string.action_hide, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
            }
        }).show();
    }
}