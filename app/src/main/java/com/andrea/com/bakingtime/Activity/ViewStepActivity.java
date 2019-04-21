package com.andrea.com.bakingtime.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.andrea.com.bakingtime.Fragment.ExoPlayerFragment;
import com.andrea.com.bakingtime.Fragment.InstructionFragment;
import com.andrea.com.bakingtime.Model.RecipeTable;
import com.andrea.com.bakingtime.R;

public class ViewStepActivity extends AppCompatActivity {

    private int stepNo;
    public static final String FRAGMENT_KEY_DATA = "STEPNO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_step);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stepNo = getIntent().getIntExtra(DetailActivity.KEY_STEPNO,0);
        inflateFragmentwithStepNo(stepNo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void inflateFragmentwithStepNo (int id){
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_KEY_DATA,id);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        ExoPlayerFragment exoPlayerFragment = new ExoPlayerFragment();
        exoPlayerFragment.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.container_exo_player, exoPlayerFragment).commit();

        InstructionFragment instructionFragment = new InstructionFragment();
        instructionFragment.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.container_instruction, instructionFragment).commit();
    }

    public void nextButton (View v){
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
