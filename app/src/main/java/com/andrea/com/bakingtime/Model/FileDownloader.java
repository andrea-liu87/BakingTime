package com.andrea.com.bakingtime.Model;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.andrea.com.bakingtime.IdlingResource.SimpleIdlingResource;

public class FileDownloader {

    private static final int DELAY_MILLIS = 3000;

    // Create an ArrayList of mTeas
    private final static Recipe[] mRecipe = new Recipe[4];

    public interface DelayerCallback{
        void onDone(Recipe[] recipes);
    }

    /**
     * This method is meant to simulate downloading a large file which has a loading time
     * delay. This could be similar to downloading an file from the internet.
     * For simplicity, in this hypothetical situation, we've provided the dummmy file
     * We simulate a delay time of {@link #DELAY_MILLIS} and once the time
     * is up we return the image back to the calling activity via a {@link DelayerCallback}.
     * @param callback used to notify the caller asynchronously
     */
    public static void downloadFile(Context context, final DelayerCallback callback,
                              @Nullable final SimpleIdlingResource idlingResource) {

        /**
         * The IdlingResource is null in production as set by the @Nullable annotation which means
         * the value is allowed to be null.
         *
         * If the idle state is true, Espresso can perform the next action.
         * If the idle state is false, Espresso will wait until it is true before
         * performing the next action.
         */
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }


        // Display a toast to let the user know the file are downloading
        String text = "Downloading in process";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Fill ArrayList with Recipe dummy objects
        mRecipe[0] = (new Recipe(0,"Orak arik tempe",null, null, 2, null));
        mRecipe[1] = (new Recipe(1, "Omelet Creamy",null, null, 4, null));
        mRecipe[2] = (new Recipe(2,"Baked Chicken", null,null,4,null));
        mRecipe[3] = (new Recipe(3,"Manggo Pudding", null, null,4, null));


        /**
         * {@link postDelayed} allows the {@link Runnable} to be run after the specified amount of
         * time set in DELAY_MILLIS elapses. An object that implements the Runnable interface
         * creates a thread. When this thread starts, the object's run method is called.
         *
         * After the time elapses, if there is a callback we return the image resource ID and
         * set the idle state to true.
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onDone(mRecipe);
                    if (idlingResource != null) {
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY_MILLIS);
    }
}
