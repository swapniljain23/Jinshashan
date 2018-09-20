package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.swapniljain.jinshashan.R;

public class JNLoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static String GOOGLE_SIGN_IN_ACCOUNT_EXTRA = "google_sign_in_account";

    private GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 1001;
    private static String TAG = JNLoginActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnlogin);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Set the dimensions of the sign-in button.
        SignInButton googleSignInButton = findViewById(R.id.google_sing_in_button);
        googleSignInButton.setSize(SignInButton.SIZE_WIDE);

        // Set onClickListener on Google sign in button.
        googleSignInButton.setOnClickListener(this);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            startListActivity(account);
        }
    }

    // View.OnClickListener methods.

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.google_sing_in_button:
                performGoogleSignIn();
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    // Private methods.

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            startListActivity(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            startListActivity(null);
        }
    }

    // Start google sign-in flow.
    private void performGoogleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // Start list activity, must be called after authentication.
    private void startListActivity(GoogleSignInAccount account) {
        Intent intent = new Intent(this, JNListActivity.class);
        intent.putExtra(GOOGLE_SIGN_IN_ACCOUNT_EXTRA, account);
        startActivity(intent);
    }
}
