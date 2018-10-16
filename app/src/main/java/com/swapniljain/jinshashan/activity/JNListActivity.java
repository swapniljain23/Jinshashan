package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.utils.JNPagerAdapter;

import java.net.URI;

public class JNListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String FIREBASE_USER_EXTRA = "firebase_user_extra";
    public static String USER_PHOTO_URI_EXTRA = "user_photo_uri_extra";

    private static String TAG = JNListActivity.class.toString();

    private DrawerLayout mDrawer;
    private FirebaseUser mFirebaseUser;
    private Uri mUserPhotoURI;

    private TextView mUserName;
    private TextView mUserEmailID;
    private ImageView mUserImageView;

    // The idling resource which will be null in production.
    @Nullable private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnlist);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        mUserName = headerView.findViewById(R.id.user_name_tv);
        mUserEmailID = headerView.findViewById(R.id.user_email_id_tv);
        mUserImageView = headerView.findViewById(R.id.user_image_view);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent.hasExtra(FIREBASE_USER_EXTRA)) {
                mFirebaseUser = intent.getParcelableExtra(FIREBASE_USER_EXTRA);
            }
            if (intent.hasExtra(USER_PHOTO_URI_EXTRA)) {
                mUserPhotoURI = intent.getParcelableExtra(USER_PHOTO_URI_EXTRA);
            }
        } else {
            mFirebaseUser = savedInstanceState.getParcelable(FIREBASE_USER_EXTRA);
            mUserPhotoURI = savedInstanceState.getParcelable(USER_PHOTO_URI_EXTRA);
        }

        populateUserInfo();

        // Tab layout.
        ViewPager viewPager = findViewById(R.id.view_pager);
        JNPagerAdapter pagerAdapter = new JNPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(FIREBASE_USER_EXTRA, mFirebaseUser);
        outState.putParcelable(USER_PHOTO_URI_EXTRA, mUserPhotoURI);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jnlist, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort_by) {
            return true;
        } else if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Do nothing. already at home.
        } else if (id == R.id.nav_favorites) {
            // Start favorite activity.
            Intent favoritesIntent = new Intent(this, JNFavoritesActivity.class);
            startActivity(favoritesIntent);
        } else if (id == R.id.nav_vow) {
            // Start take a vow activity.
            Intent takeAVowIntent = new Intent(this, JNTakeAVowActivity.class);
            startActivity(takeAVowIntent);
        } else if (id == R.id.nav_sign_out) {
            // Perform sign out.
            performSignOut();
        } else if (id == R.id.nav_share) {
            // Share app link.

        } else if (id == R.id.nav_send_feedback) {
            // Start send feedback activity.
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Private methods.

    private void performSignOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getApplicationContext(), JNLoginActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void populateUserInfo() {
        if (mFirebaseUser == null) {
            // Handle error here.
            Log.d(TAG, "Failed to login.");
        } else {
            // Set user info.
            mUserName.setText(mFirebaseUser.getDisplayName());
            mUserEmailID.setText(mFirebaseUser.getEmail());
            Log.d(TAG, "firebaseUser.getPhotoUrl()" + mFirebaseUser.getPhotoUrl());
        }

        Log.d(TAG, "USER_PHOTO_URI_EXTRA" + mUserPhotoURI);
        Picasso.get()
                .load(mUserPhotoURI)
                .resize(getResources().getInteger(R.integer.user_image_width),
                        getResources().getInteger(R.integer.user_image_height))
                .centerCrop().into(mUserImageView);
    }
}
