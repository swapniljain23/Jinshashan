package com.swapniljain.jinshashan;

import com.swapniljain.jinshashan.activity.JNDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class JNDetailActivityInstumentedTest {

    @Rule
    public ActivityTestRule<JNDetailActivity> mDetailActivityRule =
            new ActivityTestRule<>(JNDetailActivity.class);


    @Test
    public void testDetailView() {

    }

}
