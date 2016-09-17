package com.learningtdd.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.learningtdd.activity.StartActivity;

import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> mFragments;

	public ViewPagerFragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
		super(fragmentManager);
        mFragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
        Bundle args = mFragments.get(position).getArguments();
        boolean hasTitle =  args != null;
		return hasTitle ? args.getString(StartActivity.PAGE_TITLE) : "";
	}
}