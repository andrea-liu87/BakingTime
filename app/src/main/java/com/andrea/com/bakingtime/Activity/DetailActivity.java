package com.andrea.com.bakingtime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.andrea.com.bakingtime.Fragment.ExoPlayerFragment;
import com.andrea.com.bakingtime.Fragment.IngredientsFragment;
import com.andrea.com.bakingtime.Fragment.InstructionFragment;
import com.andrea.com.bakingtime.Fragment.StepsFragment;
import com.andrea.com.bakingtime.Model.Recipe;
import com.andrea.com.bakingtime.Model.Steps;
import com.andrea.com.bakingtime.R;

public class DetailActivity extends AppCompatActivity implements
 StepsFragment.OnListFragmentInteractionListener{

    private final String TAG = "TAG DETAIL-ACTIVITY";
    private Recipe mRecipe;
    public static final String CONTENT_KEY_VIDEO = "video-url-activity-key";
    public static final String CONTENT_KEY_INSTRUCTION = "instruction-activity-key";

    private FragmentManager mFragmentManager;
    private boolean mTwoPane;

    public String videoUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecipe = getIntent().getParcelableExtra(Intent.EXTRA_PACKAGE_NAME);
        mFragmentManager = getSupportFragmentManager();

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
    public void onListFragmentInteraction(Steps[] steps) {
        String videoUrl = steps[0].getVideoURL();

        String[] instructions;
        if (steps == null) {
            instructions = null;
        } else {
            instructions = new String[steps.length];
            for (int i = 0; i < steps.length; i++) {
                instructions[i] = steps[i].getDescription();
            }
        }

        if (mTwoPane == true){
            ExoPlayerFragment exoPlayerFragment = new ExoPlayerFragment();
            exoPlayerFragment.setDataMediaUri(videoUrl);
            mFragmentManager.beginTransaction().replace(R.id.container_exo_player, exoPlayerFragment).commit();

            InstructionFragment instructionFragment = new InstructionFragment();
            instructionFragment.setData(instructions);
            mFragmentManager.beginTransaction().replace(R.id.container_instruction, instructionFragment).commit();
        } else {
            Intent intent = new Intent(this, ViewStepActivity.class);
            intent.putExtra(CONTENT_KEY_VIDEO, videoUrl);
            intent.putExtra(CONTENT_KEY_INSTRUCTION, instructions);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
