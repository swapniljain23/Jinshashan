package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.model.JNListDataModel;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class JNDetailActivity extends AppCompatActivity {

    public static String LIST_MODEL_EXTRA = "LIST_MODEL_EXTRA";

    private String TAG = JNDetailActivity.class.toString();

    private JNListDataModel mDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jndetail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView heroImageView = findViewById(R.id.hero_image_view);

        Intent intent = getIntent();
        if (intent.hasExtra(LIST_MODEL_EXTRA)) {
            mDataModel = intent.getParcelableExtra(LIST_MODEL_EXTRA);
            Log.d(TAG, mDataModel.toString());

            // Set the data.
            setTitle(mDataModel.dikshaInfo.dikshaName);
            if (TextUtils.isEmpty(mDataModel.photoURL)) {
                Picasso.get()
                        .load(R.drawable.card_placeholder)
                        .into(heroImageView);
            } else {
                Picasso.get()
                        .load(mDataModel.photoURL)
                        .placeholder(R.drawable.card_placeholder)
                        .into(heroImageView);
            }
        }
    }

}
