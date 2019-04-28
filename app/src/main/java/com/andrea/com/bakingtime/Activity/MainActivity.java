package com.andrea.com.bakingtime.Activity;

import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.andrea.com.bakingtime.ApiCallbackInterface;
import com.andrea.com.bakingtime.IdlingResource.SimpleIdlingResource;
import com.andrea.com.bakingtime.Model.FileDownloader;
import com.andrea.com.bakingtime.Model.Recipe;
import com.andrea.com.bakingtime.R;
import com.andrea.com.bakingtime.Adapter.RecipeAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.clickHandler,
        FileDownloader.DelayerCallback {

    private final String TAG = "TAG MAINACTIVITY";
    private RecipeAdapter mRecipeAdapter;

    @Nullable
    private
    SimpleIdlingResource mSimpleIdlingResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        RecyclerView recipeRecyView = findViewById(R.id.recipe_rv);

        //If it is UI for tablet set the column become 3
        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int widthScreen = size.x;
        int noOfCol =1;
        if(widthScreen > 1400){noOfCol = 3;}

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, noOfCol);
        recipeRecyView.setLayoutManager(mLayoutManager);

        recipeRecyView.setHasFixedSize(true);

        mRecipeAdapter = new RecipeAdapter(this);
        recipeRecyView.setAdapter(mRecipeAdapter);

       donwloadData();
    }

    //Set of method when the user click on the grid view
    @Override
    public void onCLick(Recipe recipe) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(Intent.EXTRA_PACKAGE_NAME, recipe);
        startActivity(intent);
    }

    private void donwloadData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiCallbackInterface.DOWNLOAD_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCallbackInterface mInterface = retrofit.create(ApiCallbackInterface.class);

        Call<Recipe[]> getData = mInterface.getData("baking.json");
        getData.enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(Call<Recipe[]> call, Response<Recipe[]> response) {
                mRecipeAdapter.setRecipesData(response.body());
            }

            @Override
            public void onFailure(Call<Recipe[]> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, t.getMessage());
            }
        });
    }

    /**
     * Create a method that returns the IdlingResource variable. It will
     * instantiate a new instance of SimpleIdlingResource if the IdlingResource is null.
     * This method will only be called from test.
     */
    @VisibleForTesting
    public SimpleIdlingResource getIdlingResources (){
        if (mSimpleIdlingResources == null){
            mSimpleIdlingResources = new SimpleIdlingResource();
        }
        return mSimpleIdlingResources;
    }

    /**
     *Remove the comment // for line 117 for MainActivity test
     * This method will simulate the Recipe downloading process and delayed it for Idling Resource
     */
    @Override
    protected void onStart() {
        super.onStart();
       // FileDownloader.downloadFile(getApplicationContext(), this, mSimpleIdlingResources);
    }

    // Override onDone so when the thread in FileDownloader is finished, it returns an
    // ArrayList of Recipe objects via the callback.
    @Override
    public void onDone(Recipe[] recipes) {
        mRecipeAdapter.setRecipesData(recipes);
    }
}
