package com.swapniljain.jinshashan;

import android.content.Context;

import com.swapniljain.jinshashan.activity.JNListActivity;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.DrawerMatchers;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class JNListActivityInstrumentedTest {

    @Rule public ActivityTestRule<JNListActivity> mListActivityRule =
            new ActivityTestRule<>(JNListActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mListActivityRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.swapniljain.jinshashan", appContext.getPackageName());
    }

    @Test
    public void testHomeViewDisplay() {
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.appbar)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));
    }

    // Home view material card tests.
    @Test
    public void testMaterialCard() {
//        onView(withId(R.id.view_pager)).perform(click());

//        onView(ViewMatchers.withId(R.id.rv_jnlist))
//                .perform(RecyclerViewActions.scrollToPosition(0));
//
//        onView(ViewMatchers.withId(R.id.tv_card_title))
//                .check(matches(hasDescendant(withText("Acharya Vidyasagar"))));
//
//        onView(ViewMatchers.withId(R.id.tv_card_subtitle))
//                .check(matches(hasDescendant(withText("Dikshit since: 30/06/1968"))));
//
//        onView(ViewMatchers.withId(R.id.rv_jnlist))
//                .perform(RecyclerViewActions.scrollToPosition(1));
//
//        onView(ViewMatchers.withId(R.id.tv_card_title))
//                .check(matches(hasDescendant(withText("Muni Tarun Sagar"))));
//
//        onView(ViewMatchers.withId(R.id.tv_card_subtitle))
//                .check(matches(hasDescendant(withText("Dikshit since: 20/07/1988"))));
    }

    // Open nav drawer.

    public void openDrawer() {
        onView(withId(R.id.drawer_layout))
                .check(matches(DrawerMatchers.isClosed()))
                .perform(DrawerActions.open());
    }

    // Nav header tests.

    @Test
    public void testNavHeaderLayout() {
        onView(withId(R.id.drawer_layout))
                .check(matches(DrawerMatchers.isClosed()))
                .perform(DrawerActions.open());

        onView(withId(R.id.user_name_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.user_email_id_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.user_image_view)).check(matches(isDisplayed()));
    }

    // Nav menu tests.

    @Test
    public void testNavMenuHome() {
        openDrawer();
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_home));
    }

    @Test
    public void testNavMenuFavorites() {
        openDrawer();
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_favorites));
    }

    @Test
    public void testNavMenuVow() {
        openDrawer();
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_vow));
    }
    @Test
    public void testNavMenuSignout() {
        openDrawer();
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_sign_out));
    }
    @Test
    public void testNavMenuShare() {
        openDrawer();
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_share));
    }
    @Test
    public void testNavMenuSendFeedback() {
        openDrawer();
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_send_feedback));
    }

    // Private

}
