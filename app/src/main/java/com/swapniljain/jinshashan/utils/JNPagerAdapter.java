package com.swapniljain.jinshashan.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.swapniljain.jinshashan.fragments.JNListFragment;
import com.swapniljain.jinshashan.model.JNListDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JNPagerAdapter extends FragmentPagerAdapter {

    // Public members.
    public static String SECT = "sect";
    public static String DATA_MODEL = "data_model";
    public List<JNListDataModel> mDataModel;

    // Private members.
    private Context mContext;
    private List<String> mSectList =
            Arrays.asList("Digambar", "Shwetambar", "Terapanthi", "Sthanakvasi");

    public JNPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle arguments = new Bundle();
        arguments.putString(SECT, mSectList.get(i));

        // Filter list.
        List<JNListDataModel> filteredList = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
             filteredList = mDataModel.
                    stream().
                    filter(list -> mSectList.get(i).equalsIgnoreCase(list.sect.sect1)).
                    collect(Collectors.toList());
        }
        arguments.putParcelableArrayList(DATA_MODEL,
                (ArrayList<? extends Parcelable>) filteredList);

        Fragment fragment = new JNListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public int getCount() {
        return mSectList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mSectList.get(position);
    }

    // Setters, getters.

    public List<JNListDataModel> getmDataModel() {
        return mDataModel;
    }

    public void setmDataModel(List<JNListDataModel> mDataModel) {
        this.mDataModel = mDataModel;
    }
}
