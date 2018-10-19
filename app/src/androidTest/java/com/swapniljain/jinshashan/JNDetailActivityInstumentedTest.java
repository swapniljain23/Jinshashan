package com.swapniljain.jinshashan;

import com.swapniljain.jinshashan.activity.JNDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class JNDetailActivityInstumentedTest {

    @Rule
    public ActivityTestRule<JNDetailActivity> mDetailActivityRule =
            new ActivityTestRule<>(JNDetailActivity.class);

    @Test
    public void testDetailView() {
        // Check title, subtitles.
        onView(withId(R.id.tv_detail_title))
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_subtitle1))
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_subtitle2))
                .check(matches(isDisplayed()));

        // Check diksha info.
        onView(withId(R.id.tv_diksha_date))
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_diksha_place))
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_diksha_guru))
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_diksha_remarks))
                .check(matches(isDisplayed()));

        // Check personal info.
        onView(withId(R.id.tv_full_name))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_father_name))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_mother_name))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_dob))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_birth_place))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_personal_remarks))
                .perform(swipeUp())
                .check(matches(isDisplayed()));

        // Check recent info.
        onView(withId(R.id.tv_address))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_contact_name))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_contact_phoneno))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_contact_emailid))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
        onView(withId(R.id.tv_recent_info_remarks))
                .perform(swipeUp())
                .check(matches(isDisplayed()));
    }

    public static ViewAction swipeUp() {
        return new GeneralSwipeAction(
                Swipe.FAST,
                GeneralLocation.BOTTOM_CENTER,
                GeneralLocation.TOP_CENTER,
                Press.FINGER);
    }
}
