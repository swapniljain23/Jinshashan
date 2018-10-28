package com.swapniljain.jinshashan.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swapniljain.jinshashan.R;
import com.swapniljain.jinshashan.activity.JNDetailActivity;
import com.swapniljain.jinshashan.model.JNListDataModel;
import com.swapniljain.jinshashan.utils.JNListAdapter;
import com.swapniljain.jinshashan.utils.JNPagerAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class JNListFragment extends Fragment implements JNListAdapter.CardViewClickListener {

    private static final String TAG = JNListFragment.class.toString();

    private List<JNListDataModel> mDataModels;

    private RecyclerView mJNListRecyclerView;

    public JNListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_jnlist, container, false);
        TextView noDataFoundTextView = rootView.findViewById(R.id.tv_no_data_found);

        Bundle arguments = getArguments();
        if (arguments.containsKey(JNPagerAdapter.SECT) &&
                arguments.containsKey(JNPagerAdapter.DATA_MODEL)) {
            String sect = getArguments().getString(JNPagerAdapter.SECT);
            Log.d(TAG,"Sect: " + sect);

            mDataModels = getArguments().getParcelableArrayList(JNPagerAdapter.DATA_MODEL);
            if (mDataModels != null ) {
                Log.d(TAG, mDataModels.toString());
            }
        }

        // Setup recycler view.
        mJNListRecyclerView = rootView.findViewById(R.id.rv_jnlist);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        mJNListRecyclerView.setLayoutManager(layoutManager);

        // Populate data.
        if(mDataModels != null && mDataModels.size() > 0) {
            noDataFoundTextView.setVisibility(View.INVISIBLE);

            JNListAdapter listAdapter =  new JNListAdapter(mDataModels, this);
            mJNListRecyclerView.setAdapter(listAdapter);
        } else {
            noDataFoundTextView.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

    /*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    */

    @Override
    public void onCardViewClick(int clickedCardItemPosition) {
        // Start detail activity.
        Log.d(TAG,"onCardViewClick at position: " + clickedCardItemPosition);
        Intent intent = new Intent(getContext(), JNDetailActivity.class);
        intent.putExtra(JNDetailActivity.LIST_MODEL_EXTRA,
                        mDataModels.get(clickedCardItemPosition));

        // Animation.
        ImageView heroImageView = mJNListRecyclerView.findViewById(R.id.iv_card_image);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation
                (getActivity(), heroImageView, "hero_image");

        startActivity(intent, optionsCompat.toBundle());
    }

}
