package com.learningtdd.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.learningtdd.activity.StartActivity;

import java.util.List;

public class StartPageAdapter extends FragmentPagerAdapter {

	private int mPos = 0;

	private List<Fragment> mFragments;

	public StartPageAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
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
		setPos(position);

		return mFragments.get(position).getArguments().getString(StartActivity.PAGE_TITLE);
	}

	public int getPos() {
		return mPos;
	}

	public void setPos(int pos) {
		mPos = pos;
	}
}