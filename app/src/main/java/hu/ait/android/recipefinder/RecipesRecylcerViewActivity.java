package hu.ait.android.recipefinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import hu.ait.android.recipefinder.adapter.RecipeAdapter;

public class RecipesRecylcerViewActivity extends AppCompatActivity {
    /* Displayed recipes based on entered ingredients. */

    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipeAdapter = new RecipeAdapter(this);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // RecyclerView layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recipeAdapter);
    }

    //Intent for starting ViewRecipeDetailsActivity
    private void showViewRecipeDetailsActivity() {
        Intent intentViewRecipeDetailsActivity = new Intent(
                RecipesRecylcerViewActivity.this,
                ViewRecipeDetailsActivity.class
        );
        startActivity(intentViewRecipeDetailsActivity);
    }

    /*Called when an activity you launched exits, giving you the requestCode you started it with (allows
    you to identify who this result came from), the resultCode it returned (returned by child activity
    through setResult()), and any additional data from it. The resultCode will be RESULT_CANCELED
    if the activity explicitly returned that, didn't return any result, or crashed during this operation.*/
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (resultCode) {
//            case RESULT_OK:
//                if (requestCode == REQUEST_CODE_ADD_TODO) {
//                            AddTodoActivity.KEY_TODO);
//
//                } else if (requestCode == REQUEST_CODE_EDIT_TODO) {
//                            AddTodoActivity.KEY_TODO);
//
//                    todoEditHolder.setTodo(todoTemp.getTodo());
//                    todoEditHolder.setDone(todoTemp.isDone());
//
//                    if (todoToEditPosition != -1) {
//                        recipeAdapter.updateTodo(todoToEditPosition, todoEditHolder);
//                        todoToEditPosition = -1;
//                    }else {
//                        recipeAdapter.notifyDataSetChanged();
//                    }
//                }
//                break;
//            case RESULT_CANCELED:
//                Toast.makeText(MainActivity.this, R.string.text_cancelled, Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
}
