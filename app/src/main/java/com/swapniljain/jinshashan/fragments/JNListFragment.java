package com.swapniljain.jinshashan.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.activity.JNDetailActivity;
import com.swapniljain.jinshashan.model.JNListDataModel;
import com.swapniljain.jinshashan.utils.JNListAdapter;
import com.swapniljain.jinshashan.utils.JNPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class JNListFragment extends Fragment implements JNListAdapter.CardViewClickListener {


    private RecyclerView mJNListRecyclerView;
    private List<JNListDataModel> mDataModels;
    private static String TAG = JNListFragment.class.toString();
    private Context mContext;
    private String mSect;

    public JNListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_jnlist, container, false);

        mSect = getArguments().getString(JNPagerAdapter.SECT);
        Log.d(TAG,"Sect: " + mSect);

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

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mJNListRecyclerView = view.findViewById(R.id.rv_jnlist);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        mJNListRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume Called.");
    }

    @Override
    public void onCardViewClick(int clickedCardItemPosition) {
        // Start detail activity.
        Log.d(TAG,"onCardViewClick at position: " + clickedCardItemPosition);
        Intent intent = new Intent(getContext(), JNDetailActivity.class);
        intent.putExtra(JNDetailActivity.LIST_MODEL_EXTRA,
                        mDataModels.get(clickedCardItemPosition));
        startActivity(intent);
    }

    // Private.

    public void populateUI() {
        JNListAdapter listAdapter =  new JNListAdapter(mDataModels, this);
        mJNListRecyclerView.setAdapter(listAdapter);
    }
}
