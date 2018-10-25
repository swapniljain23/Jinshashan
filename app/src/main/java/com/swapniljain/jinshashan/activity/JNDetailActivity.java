package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.model.JNListDataModel;
import com.swapniljain.jinshashan.utils.JNUtils;

import org.w3c.dom.Text;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class JNDetailActivity extends AppCompatActivity {

    public static String LIST_MODEL_EXTRA = "LIST_MODEL_EXTRA";

    private String TAG = JNDetailActivity.class.toString();
    private JNListDataModel mDataModel;

    // Data views.
    ImageView mHeroImageView;
    TextView mTitle;
    TextView mSubtitle1;
    TextView mSubtitle2;
    TextView mDikshaDate;
    TextView mDikshaPlace;
    TextView mDikshaGuru;
    TextView mDikshaRemarks;
    TextView mFullName;
    TextView mDateOfBirth;
    TextView mFatherName;
    TextView mMotherName;
    TextView mBirthPlace;
    TextView mPersonalRemarks;
    TextView mRecentAddress;
    TextView mContactName;
    TextView mContactPhoneNo;
    TextView mContactEmailID;
    TextView mRecentInfoRemarks;
    TextView mLastUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jndetail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup close button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        // Hide title.
        // getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Favorite button.
        FloatingActionButton favoriteButton = findViewById(R.id.favorite_button);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Favorite clicked!");
            }
        });

        // Hero image.
        mHeroImageView = findViewById(R.id.hero_image_view);

        // Title subtitle.
        mTitle = findViewById(R.id.tv_detail_title);
        mSubtitle1 = findViewById(R.id.tv_detail_subtitle1);
        mSubtitle2 = findViewById(R.id.tv_detail_subtitle2);

        // Diksha info views.
        mDikshaDate = findViewById(R.id.tv_diksha_date);
        mDikshaPlace = findViewById(R.id.tv_diksha_place);
        mDikshaGuru = findViewById(R.id.tv_diksha_guru);
        mDikshaRemarks = findViewById(R.id.tv_diksha_remarks);

        // Personal info views.
        mFullName = findViewById(R.id.tv_full_name);
        mDateOfBirth = findViewById(R.id.tv_dob);
        mFatherName = findViewById(R.id.tv_father_name);
        mMotherName = findViewById(R.id.tv_mother_name);
        mBirthPlace = findViewById(R.id.tv_birth_place);
        mPersonalRemarks = findViewById(R.id.tv_personal_remarks);

        // Recent info views.
        mRecentAddress = findViewById(R.id.tv_address);
        mContactName = findViewById(R.id.tv_contact_name);
        mContactPhoneNo = findViewById(R.id.tv_contact_phoneno);
        mContactEmailID = findViewById(R.id.tv_contact_emailid);
        mRecentInfoRemarks = findViewById(R.id.tv_recent_info_remarks);
        mLastUpdated = findViewById(R.id.tv_last_update);

        Intent intent = getIntent();
        if (intent.hasExtra(LIST_MODEL_EXTRA)) {
            mDataModel = intent.getParcelableExtra(LIST_MODEL_EXTRA);
        } else if (savedInstanceState != null) {
            mDataModel = savedInstanceState.getParcelable(LIST_MODEL_EXTRA);
        }

        if (mDataModel != null) {
            Log.d(TAG, mDataModel.toString());
            populateUI();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "Close Clicked");
                this.supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable(LIST_MODEL_EXTRA, mDataModel);
    }

    // Private methods.

    public void populateUI() {
        // Set image.
        setTitle(mDataModel.dikshaInfo.dikshaName);
        if (TextUtils.isEmpty(mDataModel.photoURL)) {
            Picasso.get()
                    .load(R.drawable.card_placeholder)
                    .into(mHeroImageView);
        } else {
            Picasso.get()
                    .load(mDataModel.photoURL)
                    .placeholder(R.drawable.card_placeholder)
                    .into(mHeroImageView);
        }

        // Title, subtitle.
        mTitle.setText(mDataModel.dikshaInfo.dikshaName);
        mSubtitle1.setText(String.format(Locale.getDefault(),
                "%s, %d Years",
                mDataModel.sect.sect1,
                JNUtils.calculateAge(mDataModel.personalInfo.dateOfBirth)));
        mSubtitle2.setText(String.format("%s, %s",
                mDataModel.recentInfo.city,
                mDataModel.recentInfo.state));

        // Diksha info.
        mDikshaDate.setText(mDataModel.dikshaInfo.dikshaDate);
        mDikshaPlace.setText(String.format("%s, %s",
                mDataModel.dikshaInfo.dikshaCity,
                mDataModel.dikshaInfo.dikshaState));
        mDikshaGuru.setText(mDataModel.dikshaInfo.dikshaGuru);
        mDikshaRemarks.setText(mDataModel.dikshaInfo.remarks);

        // Personal info.
        mFullName.setText(mDataModel.personalInfo.fullName);
        mFatherName.setText(mDataModel.personalInfo.fatherNmae);
        mMotherName.setText(mDataModel.personalInfo.motherName);
        mDateOfBirth.setText(mDataModel.personalInfo.dateOfBirth);
        mBirthPlace.setText(String.format("%s, %s",
                mDataModel.personalInfo.birthCity,
                mDataModel.personalInfo.birthState));
        mPersonalRemarks.setText(mDataModel.personalInfo.remarks);

        // Recent info.
        mRecentAddress.setText(String.format("%s \n %s, %s",
                mDataModel.recentInfo.address,
                mDataModel.recentInfo.city,
                mDataModel.recentInfo.state));
        mContactName.setText(mDataModel.recentInfo.contactName);
        mContactPhoneNo.setText(mDataModel.recentInfo.contactPhoneNo);
        mContactEmailID.setText(mDataModel.recentInfo.contactEmail);
        mRecentInfoRemarks.setText(mDataModel.recentInfo.remarks);
        mLastUpdated.setText(mDataModel.recentInfo.lastUpdatedTimestamp);
    }
}
