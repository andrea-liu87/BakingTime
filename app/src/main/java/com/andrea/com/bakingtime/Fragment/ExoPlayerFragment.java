package com.andrea.com.bakingtime.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrea.com.bakingtime.Activity.DetailActivity;
import com.andrea.com.bakingtime.Model.Steps;
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
    private TextView textView;

    private String mediaUri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exoplayer, container, false);

        mExoPlayerView = view.findViewById(R.id.simple_exo_player_view);
        textView = view.findViewById(R.id.exoplayer_tv);

        if (mediaUri == null ){
            mExoPlayerView.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("No media is found for this recipe");
        } else {
            textView.setVisibility(View.INVISIBLE);
            mExoPlayerView.setVisibility(View.VISIBLE);
            initializePlayer(getActivity().getApplicationContext(), Uri.parse(mediaUri));
        }
        return view;
    }

    /**
     * This method to initialize ExoMediaPlayer
     * @param uri Video uri to be played
     */
    private void initializePlayer(Context context, Uri uri){
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

    public void setMediaUri(String url){
        mediaUri = url;
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
