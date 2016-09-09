package com.learningtdd.activity;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.learningtdd.R;
import com.learningtdd.adapter.StartPageAdapter;
import com.learningtdd.fragment.LoginFragment;
import com.learningtdd.fragment.NewAccountFragment;

import java.util.Arrays;

public class StartActivity extends FragmentActivity {

    public static final String PAGE_TITLE = "TITLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		StartPageAdapter adapter = new StartPageAdapter(getSupportFragmentManager(),
				Arrays.asList(
						LoginFragment.newInstance(getString(R.string.login)),
						NewAccountFragment.newInstance(getString(R.string.new_account))
				));

		ViewPager viewPager = (ViewPager) findViewById(R.id.start_activity_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.start_activity_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
	}


}
