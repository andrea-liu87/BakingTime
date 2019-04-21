package com.andrea.com.bakingtime.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andrea.com.bakingtime.Activity.ViewStepActivity;
import com.andrea.com.bakingtime.Model.RecipeTable;
import com.andrea.com.bakingtime.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class ExoPlayerFragment extends Fragment {

    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mExoPlayerView;

    private String mediaUri;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();

        Bundle bundle = getArguments();
        if (bundle != null){
            int stepNo = bundle.getInt(ViewStepActivity.FRAGMENT_KEY_DATA);
        getData(stepNo);}
    }

    private void getData(int id){
        String[] selectionArgs = {Integer.toString(id)};
        Cursor cursor = context.getContentResolver().query(RecipeTable.CONTENT_URI,
                null,
                RecipeTable.FIELD_COL_ID + " = ?",
                selectionArgs,
                null
        );
        cursor.moveToFirst();
        mediaUri = cursor.getString(cursor.getColumnIndex(RecipeTable.FIELD_COL_URL));
        cursor.close();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exoplayer, container, false);
        mExoPlayerView = view.findViewById(R.id.simple_exo_player_view);

        if (mediaUri == null){
            Toast.makeText(context, "No video for this step", Toast.LENGTH_LONG).show();
            Log.d("TAG", "media uri is null");
        }
        if (mediaUri != null ){
            initializePlayer(Uri.parse(mediaUri));
       }
        return view;
    }

    /**
     * This method to initialize ExoMediaPlayer
     * @param uri Video uri to be played
     */
    private void initializePlayer(Uri uri){
        if (mExoPlayer == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            mExoPlayerView.setPlayer(mExoPlayer);

            //Set media source
            String userAgent = Util.getUserAgent(context, "BakingTime");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(context, userAgent),
                    new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mExoPlayer != null){
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;}
    }
}
