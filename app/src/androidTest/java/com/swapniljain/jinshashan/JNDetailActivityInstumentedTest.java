package com.swapniljain.jinshashan;

import android.widget.ScrollView;

import com.swapniljain.jinshashan.activity.JNDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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

        // Scroll the view.
//        onView(withId(R.id.tv_recent_info_remarks))
//                .perform(ViewActions.scrollTo())
//                .check(matches(isDisplayed()));

//        // Check personal info.
//        onView(withId(R.id.tv_full_name))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_father_name))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_mother_name))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_dob))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_birth_place))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_personal_remarks))
//                .check(matches(isDisplayed()));
//
//        // Check recent info.
//        onView(withId(R.id.tv_address))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_contact_name))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_contact_phoneno))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_contact_emailid))
//                .check(matches(isDisplayed()));
//        onView(withId(R.id.tv_recent_info_remarks))
//                .check(matches(isDisplayed()));
    }

}
