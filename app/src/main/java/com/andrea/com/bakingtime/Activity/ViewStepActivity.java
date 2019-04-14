package com.andrea.com.bakingtime.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.andrea.com.bakingtime.Fragment.ExoPlayerFragment;
import com.andrea.com.bakingtime.Fragment.InstructionFragment;
import com.andrea.com.bakingtime.R;

public class ViewStepActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_step);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        ExoPlayerFragment exoPlayerFragment = new ExoPlayerFragment();
        exoPlayerFragment.setDataMediaUri(getIntent().getStringExtra(DetailActivity.CONTENT_KEY_VIDEO));
        mTransaction.replace(R.id.container_exo_player, exoPlayerFragment);

        InstructionFragment instructionFragment = new InstructionFragment();
        instructionFragment.setData(getIntent().getStringArrayExtra(DetailActivity.CONTENT_KEY_INSTRUCTION));
        mTransaction.replace(R.id.container_instruction, instructionFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
