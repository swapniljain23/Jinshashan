package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.swapniljain.jinshashan.utils.JNListAdapter;

import java.util.ArrayList;
import java.util.List;

public class JNListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = JNListActivity.class.toString();
    private RecyclerView mJNListRecyclerView;
    private List<JNListDataModel> mDataModels;
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

        // Firebase connection.
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data");

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
                populateUI();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            // Perform sign out.
            performSignOut();
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

    private void populateUI() {
        mJNListRecyclerView = (RecyclerView) findViewById(R.id.rv_jnlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mJNListRecyclerView.setLayoutManager(layoutManager);
        JNListAdapter listAdapter =  new JNListAdapter(mDataModels);
        mJNListRecyclerView.setAdapter(listAdapter);
    }
}
