package com.swapniljain.jinshashan.utils;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.swapniljain.jinshashan.fragments.JNListFragment;

import java.util.Arrays;
import java.util.List;

public class JNPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<String> mSectList =
            Arrays.asList("Digambar", "Shwetambar", "Terapanthi", "Sthanakvasi");
    public static String SECT = "sect";

    public JNPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle arguments = new Bundle();
        arguments.putString(SECT, mSectList.get(i));
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

}
