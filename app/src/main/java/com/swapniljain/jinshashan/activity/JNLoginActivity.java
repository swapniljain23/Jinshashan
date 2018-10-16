package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.swapniljain.jinshashan.R;

import java.util.Arrays;
import java.util.List;

public class JNLoginActivity extends AppCompatActivity {

    private static int RC_SIGN_IN = 1001;
    private static String TAG = JNLoginActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnlogin);

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in.
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d(TAG, "PhotoURL: " + user.getPhotoUrl());
                startListActivity(user, user.getPhotoUrl());
            } else {
                // Sign in failed.
                Log.d(TAG, "Failed to login.");
            }
        } else {
            // Wrong request code. Do nothing.
            Log.d(TAG, "Failed to login.");
        }
    }

    // Start list activity, must be called after authentication.
    private void startListActivity(FirebaseUser user, Uri userPhotoUrl) {
        Intent intent = new Intent(this, JNListActivity.class);
        intent.putExtra(JNListActivity.FIREBASE_USER_EXTRA, user);
        intent.putExtra(JNListActivity.USER_PHOTO_URI_EXTRA, userPhotoUrl);
        startActivity(intent);
    }
}
