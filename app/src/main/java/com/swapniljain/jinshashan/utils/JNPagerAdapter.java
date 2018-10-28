package com.swapniljain.jinshashan.utils;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.common.collect.FluentIterable;
import com.swapniljain.jinshashan.fragments.JNListFragment;
import com.swapniljain.jinshashan.model.JNListDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JNPagerAdapter extends FragmentStatePagerAdapter {

    // Public members.
    public static final String SECT = "sect";
    public static final String DATA_MODEL = "data_model";
    public List<JNListDataModel> mDataModel;

    // Private members.
    private final List<String> mSectList =
            Arrays.asList("Digambar", "Shwetambar", "Terapanthi", "Sthanakvasi");
    private static String TAG = JNPagerAdapter.class.toString();

    public JNPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        Bundle arguments = new Bundle();
        arguments.putString(SECT, mSectList.get(i));

        // Filter list.
        if (mDataModel != null) {
            List<JNListDataModel> filteredList = FluentIterable.from(mDataModel)
                    .filter(list -> mSectList.get(i).equalsIgnoreCase(list.sect.sect1))
                    .toList();
            ArrayList<JNListDataModel> filterdArraylist = new ArrayList<>(filteredList);

            arguments.putParcelableArrayList(DATA_MODEL, filterdArraylist);
        }

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

    public List<JNListDataModel> getDataModel() {
        return mDataModel;
    }

    public void setDataModel(List<JNListDataModel> dataModel) {
        this.mDataModel = dataModel;
    }
}
