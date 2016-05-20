package hu.ait.android.recipefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import hu.ait.android.recipefinder.data.Ingredient;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_INGREDIENT = "KEY_INGREDIENT";
    private LinearLayout layoutContent;
    private EditText etIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutContent = (LinearLayout) findViewById(R.id.layoutContent);

//        ingredientAdapter = new IngredientAdapter(foodList, this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(foodAdapter);

        etIngredients = (EditText) findViewById(R.id.etIngredients);

        Button btnRecipe = (Button) findViewById(R.id.btnRecipe);
        btnRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveIngredients();
            }
        });
    }

    private void saveIngredients() {
        if (!"". equals(etIngredients.getText().toString())) {
            Intent intentResult = new Intent();
            Ingredient ingredientResult = null;
            ingredientResult = new Ingredient();

            ingredientResult.setIngredientNames(etIngredients.getText().toString());

            intentResult.putExtra(KEY_INGREDIENT, ingredientResult);
            setResult(RESULT_OK, intentResult);
            finish();
        } else {
            etIngredients.setError("This field is required!");
        }

    }
}
