package hu.ait.android.recipefinder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import hu.ait.android.recipefinder.R;
import hu.ait.android.recipefinder.data.Ingredient;

/**
 * Created by User on 5/21/2016.
 */
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvCityName;
        public ImageButton btnDelete;
        LinearLayout cityRow;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.tvCityName);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDelete);
            cityRow = (LinearLayout) itemView.findViewById(R.id.row_ingredient);
        }
    }

    private List<Ingredient> ingredientList;
    private Context context;
    private int lastPosition = -1;

    public IngredientAdapter(List<Ingredient> ingredientList, Context context) {
        this.ingredientList = ingredientList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_ingredients, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tvCityName.setText(ingredientList.get(position).getIngredientNames());

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeIngredient(position);
            }
        });

//        viewHolder.cityRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentStart = new Intent(context, DetailsActivity.class);
//                String cityName = cityList.get(position).getCityName();
//                intentStart.putExtra("CITY_STRING", cityName);
//                context.startActivity(intentStart);
//            }
//        });
        setAnimation(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void addIngredient (Ingredient city) {
        city.save();
        ingredientList.add(city);
        notifyDataSetChanged();
    }

    public void updateCity(int index, Ingredient ingredient) {
        ingredientList.set(index, ingredient);
        ingredient.save();
        notifyItemChanged(index);
    }

    public void removeIngredient(int index) {
        // remove it from the DB
        ingredientList.get(index).delete();
        // remove it from the list
        ingredientList.remove(index);
        notifyDataSetChanged();
    }

    public Ingredient getIngredient (int i) {
        return ingredientList.get(i);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}



