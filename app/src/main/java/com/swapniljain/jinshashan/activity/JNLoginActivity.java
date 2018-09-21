package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.swapniljain.jinshashan.R;

import java.util.Arrays;
import java.util.List;

public class JNLoginActivity extends AppCompatActivity {

    public static String FIREBASE_USER_EXTRA = "firebase_user";

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
                startListActivity(user);
            } else {
                // Sign in failed.
            }
        } else {
            // Wrong request code. Do nothing.
        }
    }

    // Start list activity, must be called after authentication.
    private void startListActivity(FirebaseUser user) {
        Intent intent = new Intent(this, JNListActivity.class);
        intent.putExtra(FIREBASE_USER_EXTRA, user);
        startActivity(intent);
    }
}
