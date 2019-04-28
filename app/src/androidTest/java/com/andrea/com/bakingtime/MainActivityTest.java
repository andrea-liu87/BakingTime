package com.andrea.com.bakingtime;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.andrea.com.bakingtime.Activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

@Rule
    public final ActivityTestRule<MainActivity> mTestRule = new ActivityTestRule<>(MainActivity.class);

private IdlingResource mIdlingResource;

    // Registers any resource that needs to be synchronized with Espresso before
    // the test is run.
    @Before
    public void registerIdlingResource() {
        mIdlingResource = mTestRule.getActivity().getIdlingResources();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void showRecipe(){
        onView(withId(R.id.recipe_rv)).check(matches(isDisplayed()));
    }

    //Unregister resources when not needed to avoid malfunction
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null){
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
