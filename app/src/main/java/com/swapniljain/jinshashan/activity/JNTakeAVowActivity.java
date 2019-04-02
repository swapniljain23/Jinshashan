package com.swapniljain.jinshashan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.dialogs.JNAcceptVowDialogFragment;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class JNTakeAVowActivity extends AppCompatActivity {

    private String TAG = JNTakeAVowActivity.class.getName();
    private TextView mVowTextView;
    private TextView mVowMessage;
    private Button mGenerateNewVowButton;
    private Button mAcceptThisVowbutton;
    private int mTotalVowGenerated;
    private ArrayList<String> mVowList = new ArrayList<>();
    private int MAX_CHANCE_ALLOWED = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jntake_avow);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        mTotalVowGenerated = 0;
        generateVowList();

        mVowTextView = findViewById(R.id.tv_vow);
        mVowMessage = findViewById(R.id.tv_vow_message);
        mGenerateNewVowButton = findViewById(R.id.generate_a_vow);
        mGenerateNewVowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTotalVowGenerated >= MAX_CHANCE_ALLOWED) {
                    return;
                }
                int randomNumber = (int)(Math.random() * mVowList.size());
                assert randomNumber < mVowList.size() : "Invalid random number.";
                mVowTextView.setText(mVowList.get(randomNumber));
                mAcceptThisVowbutton.setVisibility(View.VISIBLE);
                mTotalVowGenerated++;

                if (mTotalVowGenerated == MAX_CHANCE_ALLOWED) {
                    mGenerateNewVowButton.setEnabled(false);
                    mVowMessage.setText(R.string.max_attempts_tried_msg);
                }
            }
        });
        mAcceptThisVowbutton = findViewById(R.id.accept_a_vow);
        mAcceptThisVowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JNAcceptVowDialogFragment dialogFragment = new JNAcceptVowDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "vow_accepted");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void generateVowList() {
        mVowList.clear();
        mVowList.add("Take some sort of fast today. (i.e. only eating 3 meals)");
        mVowList.add("Don't listen to vulgar music today.");
        mVowList.add("Refrain from your favorite hobby for a day.");
        mVowList.add("Understand different viewpoints by reading about different religions.");
        mVowList.add("Bow down to your parents.");
        mVowList.add("Practice positive self-talk.");
        mVowList.add("Limit using your phone today to 1 hour total.");
        mVowList.add("Visit the elderly.");
        mVowList.add("Take a Maun Vrat (vow of silence) for 10 minutes.");
        mVowList.add("Buy less items at the grocery store and donate the money you save.");
        mVowList.add("Listen to a Jain stavan on YouTube.");
        mVowList.add("Replace your TV time with meditation or prayer time.");
        mVowList.add("Don't walk on grass today.");
        mVowList.add("Spend free time volunteering.");
        mVowList.add("Recycle or donate 4 things you don’t need.");
        mVowList.add("Write a reflection on your day.");
        mVowList.add("Drive at or below the speed limit.");
        mVowList.add("Read one Young Minds article today (youngminds.yja.org)");
        mVowList.add("Eat all meals before night.");
        mVowList.add("Give up the snooze button on your alarm clock.");
        mVowList.add("Wear the first outfit you put on in the morning.");
        mVowList.add("Wish Jai Jinendra to your family members.");
        mVowList.add("Let someone go in front of you in line.");
        mVowList.add("Meditate for 20 minutes.");
        mVowList.add("Make a vegan meal for a non-vegan friend.");
        mVowList.add("Unplug from your phone on your commute.");
        mVowList.add("Don't watch TV (Netflix counts!) today.");
        mVowList.add("Limit your shower to five minutes.");
        mVowList.add("Eat a vegan meal.");
        mVowList.add("Give a stranger a compliment.");
        mVowList.add("Donate food to a homeless shelter.");
        mVowList.add("Give something to a deserving person today.");
        mVowList.add("Call your parents and tell them how much you appreciate them.");
        mVowList.add("Eat only X foods today.");
        mVowList.add("Give up Twitter for a day.");
        mVowList.add("Give up Facebook for a day.");
        mVowList.add("Give up Instagram for a day.");
        mVowList.add("Give up Whatsapp for a day.");
        mVowList.add("Say 5 Navkars before going to sleep.");
        mVowList.add("Give up your favorite food or drink today.");
        mVowList.add("Go to your local sangh or mandir.");
        mVowList.add("Give up your favorite food or restaurant.");
        mVowList.add("Be kind to yourself (eg. stop comparing yourself to others for a day).");
        mVowList.add("Learn more about a social issue (environment, child poverty).");
        mVowList.add("Give food to someone in need.");
        mVowList.add("Say 5 Navkars before lunch and dinner.");
        mVowList.add("Take the time to listen to someone.");
        mVowList.add("Say 3 nice things to the 3 most important people in your life.");
        mVowList.add("Read spiritual texts for 10 minutes.");
        mVowList.add("Get to know your neighbors/roommates better.");
    }
}
