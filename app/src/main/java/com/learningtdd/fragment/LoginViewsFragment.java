package com.learningtdd.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.learningtdd.R;
import com.learningtdd.adapter.ViewPagerFragmentAdapter;

import java.util.Arrays;

public class LoginViewsFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_logins, container, false);

        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getChildFragmentManager(),
                Arrays.asList(
                        LoginFragment.newInstance(getString(R.string.login)),
                        NewAccountFragment.newInstance(getString(R.string.new_account))
                ));

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_logins_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_logins_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

		return view;
	}

    public static LoginViewsFragment newInstance() {
        return new LoginViewsFragment();
    }
}