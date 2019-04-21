package com.andrea.com.bakingtime.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.andrea.com.bakingtime.Fragment.ExoPlayerFragment;
import com.andrea.com.bakingtime.Fragment.IngredientsFragment;
import com.andrea.com.bakingtime.Fragment.InstructionFragment;
import com.andrea.com.bakingtime.Fragment.StepsFragment;
import com.andrea.com.bakingtime.Model.Ingredients;
import com.andrea.com.bakingtime.Model.IngredientsTable;
import com.andrea.com.bakingtime.Model.Recipe;
import com.andrea.com.bakingtime.Model.RecipeTable;
import com.andrea.com.bakingtime.Model.Steps;
import com.andrea.com.bakingtime.R;

public class DetailActivity extends AppCompatActivity implements
 StepsFragment.OnListFragmentInteractionListener{

    private final String TAG = "TAG DETAIL-ACTIVITY";
    public static final String KEY_STEPNO = "STEP-NO";

    private boolean mTwoPane;
    private int stepNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Recipe mRecipe = getIntent().getParcelableExtra(Intent.EXTRA_PACKAGE_NAME);
        saveIngredients(mRecipe.getIngredients());
        saveSteps(mRecipe.getSteps());
        FragmentManager mFragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.container_exo_player) != null){
            mTwoPane = true;
            ExoPlayerFragment exoPlayerFragment = new ExoPlayerFragment();
            mFragmentManager.beginTransaction().replace(R.id.container_exo_player, exoPlayerFragment).commit();

            InstructionFragment instructionFragment = new InstructionFragment();
            mFragmentManager.beginTransaction().replace(R.id.container_instruction, instructionFragment).commit();
        }
        else{
        TextView title = findViewById(R.id.detail_title_tv);
        title.setText(mRecipe.getName());}

        IngredientsFragment mIngrFragment = new IngredientsFragment();
        mFragmentManager.beginTransaction().replace(R.id.container_ingredients, mIngrFragment).commit();

        StepsFragment mStepFragment = new StepsFragment();
        mFragmentManager.beginTransaction().replace(R.id.container_steps, mStepFragment).commit();
    }


    @Override
    public void onListFragmentInteraction(Steps steps) {
        stepNo = steps.getId();
        if (mTwoPane){
            inflateFragmentwithStepNo(stepNo);
        } else {
            Intent intent = new Intent(this, ViewStepActivity.class);
            intent.putExtra(KEY_STEPNO, stepNo);
            startActivity(intent);
        }
    }

    /**
     * This method will saved whole steps from selected recipe inside RecipeTable from Content
     * Provider
     * @param steps the wholes steeps for this recipe
     */
    private void saveSteps(Steps[] steps){
        for(Steps step: steps){
            ContentValues contentValues = new ContentValues();
            contentValues.put(RecipeTable.FIELD_COL_ID, step.getId());
            contentValues.put(RecipeTable.FIELD_COL_DESCRIPTION, step.getDescription());
            contentValues.put(RecipeTable.FIELD_COL_URL, step.getVideoURL());
            getContentResolver().insert(RecipeTable.CONTENT_URI, contentValues);
        }
    }

    private void saveIngredients(Ingredients[] ingredients){
        for (Ingredients ingredient : ingredients){
            ContentValues contentValues = new ContentValues();
            contentValues.put(IngredientsTable.FIELD_COL_INGREDIENTS, ingredient.getIngredient());
            getContentResolver().insert(IngredientsTable.CONTENT_URI, contentValues);
        }
    }

    /**
     * This method will inflate ExoPlayerFragment and InstructionFragment with the step id given
     * @param id step id from recipe
     */
    private void inflateFragmentwithStepNo (int id){
        Bundle bundle = new Bundle();
        bundle.putInt(ViewStepActivity.FRAGMENT_KEY_DATA,id);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        ExoPlayerFragment exoPlayerFragment = new ExoPlayerFragment();
        exoPlayerFragment.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.container_exo_player, exoPlayerFragment).commit();

        InstructionFragment instructionFragment = new InstructionFragment();
        instructionFragment.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.container_instruction, instructionFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonNext (View v){
        Cursor cursor = getContentResolver().query(RecipeTable.CONTENT_URI,
                null,
                null,
                null,
                null);
        int totalStep = cursor.getCount();
        if (stepNo < totalStep-1){
            stepNo = stepNo + 1;
        }
        else {stepNo = 0;}
        inflateFragmentwithStepNo(stepNo);
        cursor.close();
    }
}
