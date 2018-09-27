package com.swapniljain.jinshashan.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    public JNListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_jnlist, container, false);

        String sect = getArguments().getString(JNPagerAdapter.SECT);
        Log.d(TAG,"Sect: " + sect);

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
                    mDataModels.add(dataModel);
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
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume Called.");
    }

    @Override
    public void onCardViewClick(int clickedCardItemPosition) {
        Log.d(TAG,"onCardViewClick at position: " + clickedCardItemPosition);
    }

    // Private.

    public void populateUI() {
        mJNListRecyclerView = getActivity().findViewById(R.id.rv_jnlist);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());
        mJNListRecyclerView.setLayoutManager(layoutManager);
        JNListAdapter listAdapter =  new JNListAdapter(mDataModels, this);
        mJNListRecyclerView.setAdapter(listAdapter);
    }
}
