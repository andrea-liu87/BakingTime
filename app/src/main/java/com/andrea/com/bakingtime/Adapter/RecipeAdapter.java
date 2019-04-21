package com.andrea.com.bakingtime.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrea.com.bakingtime.Model.Recipe;
import com.andrea.com.bakingtime.R;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private final clickHandler mClickHandler;
    private Recipe[] recipesData;

    public RecipeAdapter(clickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }

    public interface clickHandler{
        void onCLick(Recipe recipe);
    }

    public void setRecipesData (Recipe[] recipes){
        recipesData = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_item, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * Bind data to viewHolder
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bindViewHolder(recipesData[position]);
    }

    @Override
    public int getItemCount() {
        if (recipesData == null){return 0;}
        return recipesData.length;
    }


    /**
     * Inner class for RecyclerView Holder model
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener{

        final TextView itemTv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemTv = itemView.findViewById(R.id.recipe_item_tv);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Recipe selectedRecipe = recipesData[position];
            mClickHandler.onCLick(selectedRecipe);
        }

        void bindViewHolder(Recipe recipe){
            itemTv.setText(recipe.getName());
        }
    }
}
