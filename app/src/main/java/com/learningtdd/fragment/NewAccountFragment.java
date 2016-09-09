package com.learningtdd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learningtdd.R;
import com.learningtdd.activity.StartActivity;

public class NewAccountFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_new_account, container, false);

		return view;
	}

	public static LoginFragment newInstance(String title) {
		LoginFragment fragment = new LoginFragment();
		Bundle args = new Bundle();
		args.putString(StartActivity.PAGE_TITLE, title);
		fragment.setArguments(args);
		return fragment;
	}
}