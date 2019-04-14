package com.andrea.com.bakingtime.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrea.com.bakingtime.Adapter.IngredientRecyclerViewAdapter;
import com.andrea.com.bakingtime.Model.Ingredients;
import com.andrea.com.bakingtime.Model.Recipe;
import com.andrea.com.bakingtime.R;


/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class IngredientsFragment extends Fragment {

    private int mColumnCount = 1;

    private Ingredients[] ingredientsData;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public IngredientsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recipe recipe = getActivity().getIntent().getParcelableExtra(Intent.EXTRA_PACKAGE_NAME);
        ingredientsData = recipe.getIngredients();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient_layout, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycl_view_ingredient_fragment);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), mColumnCount));
        recyclerView.setAdapter(new IngredientRecyclerViewAdapter(ingredientsData));

        return view;
    }
}
