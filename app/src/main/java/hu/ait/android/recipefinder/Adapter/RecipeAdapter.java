package hu.ait.android.recipefinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.ait.android.recipefinder.R;
import hu.ait.android.recipefinder.RecipeDetailsActivity;
import hu.ait.android.recipefinder.RecipeListActivity;
import hu.ait.android.recipefinder.data.Recipe;

// Provides the views that represent items in a data set.
public class RecipeAdapter
        extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    //ViewHolders belong to the adapter.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Parameters: Everything that is part of each recipe item.
        public ImageView ivRecipeImage;
        public TextView tvRecipeName;
        public TextView tvRecipeID;
        public TextView tvMissingIngredients;
        public CardView recipeRow;

        public ViewHolder(View itemView) {
            super(itemView);
            ivRecipeImage = (ImageView) itemView.findViewById(R.id.ivRecipeImage);
            tvRecipeName = (TextView) itemView.findViewById(R.id.tvRecipeName);
            tvRecipeID = (TextView) itemView.findViewById(R.id.tvRecipeID);
            tvMissingIngredients = (TextView) itemView.findViewById(R.id.tvMissingIngredients);
            recipeRow = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    private Context context;
    private List<Recipe> recipes = new ArrayList<>();

    public RecipeAdapter(Context context) {
        this.context = context;
    }

    /* Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an
     item. */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(rowView);
    }

    /*Called by RecyclerView to display the data at the specified position. Should update the contents
    of the itemView to reflect the item at the given position.*/
    @Override
    public void onBindViewHolder(final ViewHolder holder,
                                 final int position) {
        //UI elements from recipe_card.
        holder.tvRecipeName.setText(recipes.get(position).getRecipeName());
        holder.tvRecipeID.setText("ID: "+recipes.get(position).getRecipeId());
        holder.tvMissingIngredients.setText("Missing Ingredients: "+recipes.get(position).getMissingIngredients());
        holder.recipeRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart = new Intent(context, RecipeDetailsActivity.class);
                Integer recipeDetailsID = recipes.get(position).getRecipeId();
                intentStart.putExtra("RECIPE_ID", recipeDetailsID);
                context.startActivity(intentStart);
            }
        });
    }


    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        notifyDataSetChanged();
    }
}

