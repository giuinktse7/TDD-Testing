package com.learningtdd.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.learningtdd.R;
import com.learningtdd.facebookUtil.userEvent.UserEvent;
import com.learningtdd.facebookUtil.userEvent.UserTracker;
import com.learningtdd.fragment.DashboardFragment;
import com.learningtdd.fragment.LoginViewsFragment;


public class StartActivity extends FragmentActivity {

    public static final String PAGE_TITLE = "TITLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_start_fragment_holder, LoginViewsFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();*/

		initializeFbDataTracking();
	}

    private void initializeFbDataTracking() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        UserTracker.getInstance().startTracking();

        UserTracker.getInstance().addListener(getTaskId(), UserEvent.LOGIN, () -> gotoDashboard());
    }

    private void gotoDashboard() {
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_start_fragment_holder,
                DashboardFragment.newInstance()).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        UserTracker.getInstance().unsubscribe(getTaskId());
    }
}
