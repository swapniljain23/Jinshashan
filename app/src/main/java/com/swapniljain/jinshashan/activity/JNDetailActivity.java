package com.swapniljain.jinshashan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.model.JNListDataModel;

public class JNDetailActivity extends Activity {

    public static String LIST_MODEL_EXTRA = "LIST_MODEL_EXTRA";

    private String TAG = JNDetailActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jndetail);

        Intent intent = getIntent();
        if (intent.hasExtra(LIST_MODEL_EXTRA)) {
            JNListDataModel listDataModel = intent.getParcelableExtra(LIST_MODEL_EXTRA);
            Log.d(TAG, listDataModel.toString());
        }
    }

}
