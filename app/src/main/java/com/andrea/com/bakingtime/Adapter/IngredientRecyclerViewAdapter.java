package com.andrea.com.bakingtime.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrea.com.bakingtime.Model.Ingredients;
import com.andrea.com.bakingtime.R;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Ingredients[]}
 */
public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {

    private final Ingredients[] ingredientsData;

    public IngredientRecyclerViewAdapter(Ingredients[] ingredients) {
        ingredientsData = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindViewHolder(ingredientsData[position]);
    }

    @Override
    public int getItemCount() {
        if (ingredientsData == null){return 0;}
        return ingredientsData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView itemListTV1;
        public final TextView itemListTV2;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            itemListTV1 = view.findViewById(R.id.ingredient_item1);
            itemListTV2 = view.findViewById(R.id.ingredient_item2);
        }

        void bindViewHolder(Ingredients ingredient){
            itemListTV1.setText(Double.toString(ingredient.getQuantity())
            + " " + ingredient.getMeasure());
            itemListTV2.setText(ingredient.getIngredient());
        }
    }
}
