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

public class JNPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<String> mSectList =
            Arrays.asList("Digambar", "Shwetambar", "Terapanthi", "Sthanakvasi");

    public List<JNListDataModel> mDataModel;
    public static String SECT = "sect";
    public static String DATA_MODEL = "data_model";

    public JNPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle arguments = new Bundle();
        arguments.putString(SECT, mSectList.get(i));
        arguments.putParcelableArrayList(DATA_MODEL, (ArrayList<? extends Parcelable>) mDataModel);
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
