package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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

public class JNListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = JNListActivity.class.toString();
    private DrawerLayout mDrawer;

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

        Intent intent = getIntent();
        if (intent.hasExtra(JNLoginActivity.FIREBASE_USER_EXTRA)) {
            FirebaseUser firebaseUser =
                    intent.getParcelableExtra(JNLoginActivity.FIREBASE_USER_EXTRA);
            if (firebaseUser == null) {
                // Handle error here.
            } else {
                // Set user info.
                View headerView = navigationView.getHeaderView(0);
                TextView userName = headerView.findViewById(R.id.user_name_tv);
                TextView userEmailId = headerView.findViewById(R.id.user_email_id_tv);
                ImageView userImageView = headerView.findViewById(R.id.user_image_view);
                userName.setText(firebaseUser.getDisplayName());
                userEmailId.setText(firebaseUser.getEmail());
                Log.d(TAG, "Photo Url: " + firebaseUser.getPhotoUrl());
                Picasso.get().load(firebaseUser.getPhotoUrl())
                        .resize(getResources().getInteger(R.integer.user_image_width),
                                getResources().getInteger(R.integer.user_image_height))
                        .centerCrop().into(userImageView);
            }
        }

        // Tab layout.
        ViewPager viewPager = findViewById(R.id.view_pager);
        JNPagerAdapter pagerAdapter = new JNPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
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
}
