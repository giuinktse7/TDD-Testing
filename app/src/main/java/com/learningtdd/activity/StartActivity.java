package com.learningtdd.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.learningtdd.R;
import com.learningtdd.adapter.StartPageAdapter;
import com.learningtdd.fragment.LoginFragment;
import com.learningtdd.fragment.NewAccountFragment;

import java.util.Arrays;

public class StartActivity extends FragmentActivity {
	private StartPageAdapter mStartAdapter;
	private ViewPager mPager;

	private final Fragment mLoginFragment = new LoginFragment();
	private final Fragment mNewAccountFragment = new NewAccountFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		mStartAdapter = new StartPageAdapter(getSupportFragmentManager(), Arrays.asList(mLoginFragment, mNewAccountFragment));
		mPager = (ViewPager) findViewById(R.id.start_activity_view_pager);
		mPager.setAdapter(mStartAdapter);
	}


}
