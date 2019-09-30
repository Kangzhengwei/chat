package com.sopho.chat.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/5/7
 * Description:
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] mTitle;

    public ViewPagerAdapter(FragmentManager fm, String[] tabs, List<Fragment> fragments) {
        super(fm);
        mTitle = tabs;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
