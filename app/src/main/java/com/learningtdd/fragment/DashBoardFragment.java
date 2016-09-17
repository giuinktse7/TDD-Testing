package com.learningtdd.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learningtdd.R;
import com.learningtdd.adapter.ViewPagerFragmentAdapter;

import java.util.Arrays;

public class DashboardFragment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getChildFragmentManager(),
                Arrays.asList(
                        new ConversationsFragment(),
                        new OptionsFragment()
                ));

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.fragment_dashboard_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.fragment_dashboard_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); ++i) {
            Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(R.drawable.bulbasaur);
        }

		return view;
	}
}