package com.learningtdd.activity;
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

	private static final String LOGIN_PAGE_TITLE = "Login";
	private static final String CREATE_ACCOUNT_PAGE_TITLE = "Create account";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		StartPageAdapter adapter = new StartPageAdapter(getSupportFragmentManager(),
				Arrays.asList(
						LoginFragment.newInstance(LOGIN_PAGE_TITLE),
						NewAccountFragment.newInstance(CREATE_ACCOUNT_PAGE_TITLE)
				));

		ViewPager viewPager = (ViewPager) findViewById(R.id.start_activity_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.start_activity_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
	}


}
