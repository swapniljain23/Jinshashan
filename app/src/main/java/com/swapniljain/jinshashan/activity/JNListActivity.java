package com.swapniljain.jinshashan.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.GravityCompat;
import androidx.test.espresso.IdlingResource;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.model.JNListDataModel;
import com.swapniljain.jinshashan.utils.JNPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class JNListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String FIREBASE_USER_EXTRA = "firebase_user_extra";
    public static String USER_PHOTO_URI_EXTRA = "user_photo_uri_extra";
    public static String DATA_MODEL = "data_model";
    public static String PREFERENCE_NAME = "list_activity_preference";
    public static String WIDGET_TITLE = "widget_title";
    public static String WIDGET_SUBTITLE = "widget_subtitle";
    public static String WIDGET_HERO_IMAGE_URL = "widget_hero_image_url";
    private static String TAG = JNListActivity.class.toString();

    private DrawerLayout mDrawer;
    private TextView mUserName;
    private TextView mUserEmailID;
    private ImageView mUserImageView;
    private ProgressBar mProgressBar;
    private TextView mDatabaseError;

    private FirebaseUser mFirebaseUser;
    private Uri mUserPhotoURI;

    private List<JNListDataModel> mDataModels;

    // The idling resource which will be null in production.
    @Nullable private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if(mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnlist);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = findViewById(R.id.progress_circular);
        mDatabaseError = findViewById(R.id.tv_database_error);

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

            // Start firebase connection and fetch data.
            fetchData();

        } else {
            mFirebaseUser = savedInstanceState.getParcelable(FIREBASE_USER_EXTRA);
            mUserPhotoURI = savedInstanceState.getParcelable(USER_PHOTO_URI_EXTRA);
            mDataModels = savedInstanceState.getParcelableArrayList(DATA_MODEL);
            populateTabs();
        }

        // Set user info.
        populateUserInfo();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(FIREBASE_USER_EXTRA, mFirebaseUser);
        outState.putParcelable(USER_PHOTO_URI_EXTRA, mUserPhotoURI);
        outState.putParcelableArrayList(DATA_MODEL, (ArrayList<? extends Parcelable>) mDataModels);
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
        if(mUserPhotoURI != null) {
            Picasso.get()
                    .load(mUserPhotoURI)
                    .resize(getResources().getInteger(R.integer.user_image_width),
                            getResources().getInteger(R.integer.user_image_height))
                    .centerCrop().into(mUserImageView);
        }
    }

    private void populateTabs() {
        // Tab layout.
        ViewPager viewPager = findViewById(R.id.view_pager);
        JNPagerAdapter pagerAdapter = new JNPagerAdapter(this, getSupportFragmentManager());
        pagerAdapter.setmDataModel(mDataModels);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void fetchData() {
        // Firebase connection.
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data");

        // Only for testing.
        if(mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        // Start progress bar.
        mProgressBar.setVisibility(View.VISIBLE);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                mDataModels = new ArrayList<>();
                for (DataSnapshot snapshot: children) {
                    JNListDataModel dataModel = new JNListDataModel(snapshot);
                    Log.d(TAG,dataModel.toString());
                    mDataModels.add(dataModel);
                }

                // Hide error string, if any.
                mDatabaseError.setVisibility(View.INVISIBLE);

                // Stop progress bar.
                mProgressBar.setVisibility(View.INVISIBLE);

                // Refresh ui.
                populateTabs();

                // Save first object (if exist) in shared preference so it can be used to display
                // in widgets.
                if (mDataModels.size() > 0) {
                    writeToSharedPreference(mDataModels.get(0));
                }

                // Only for testing.
                if(mIdlingResource != null) {
                    mIdlingResource.setIdleState(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // mProgressBar.setVisibility(View.INVISIBLE);
                Log.w(TAG, "Failed to read value.", error.toException());

                // Show an error.
                mDatabaseError.setVisibility(View.VISIBLE);

                // Stop progress bar.
                mProgressBar.setVisibility(View.INVISIBLE);

                // Only for testing.
                if(mIdlingResource != null) {
                    mIdlingResource.setIdleState(true);
                }
            }
        });
    }

    public void writeToSharedPreference(JNListDataModel dataModel) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SharedPreferences sharedPreferences =
                        getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(WIDGET_TITLE, dataModel.dikshaInfo.dikshaName);
                editor.putString(WIDGET_SUBTITLE, dataModel.sect.sect1 + ", 60 Years, Jabalpur MP");
                editor.putString(WIDGET_HERO_IMAGE_URL, dataModel.photoURL);

                editor.apply();
                return null;
            }
        }.execute();
    }
}
