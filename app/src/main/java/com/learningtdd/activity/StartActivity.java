package com.learningtdd.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.learningtdd.R;
import com.learningtdd.facebookUtil.userEvent.UserEvent;
import com.learningtdd.facebookUtil.userEvent.UserTracker;
import com.learningtdd.fragment.DashboardFragment;
import com.learningtdd.fragment.LoginViewsFragment;
import com.learningtdd.fragment.OptionsFragment;


public class StartActivity extends FragmentActivity {

    public static final String PAGE_TITLE = "TITLE";
    FrameLayout mFragmentHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

        mFragmentHolder = (FrameLayout) findViewById(R.id.activity_start_fragment_holder);

        addFragment(LoginViewsFragment.newInstance());

		initializeFbDataTracking();
	}

    private void initializeFbDataTracking() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        UserTracker.getInstance().startTracking();

        UserTracker.getInstance().addListener(getTaskId(), UserEvent.LOGIN, () -> gotoFragment(new DashboardFragment()));
        UserTracker.getInstance().addListener(getTaskId(), UserEvent.LOGOUT, () -> gotoFragment(new LoginViewsFragment()));
    }

    private void gotoFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_start_fragment_holder,
                        fragment)
                .commit();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_start_fragment_holder,
                        fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        UserTracker.getInstance().unsubscribe(getTaskId());
    }
}
