package com.learningtdd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.learningtdd.R;
import com.learningtdd.activity.StartActivity;
import com.learningtdd.facebookUtil.userEvent.UserEvent;
import com.learningtdd.facebookUtil.userEvent.UserTracker;
import com.learningtdd.util.TalkingProgressBar;

public class LoginFragment extends Fragment {

    CallbackManager mCallbackManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);

        mCallbackManager = CallbackManager.Factory.create();

        Button btnLogin = (Button) view.findViewById(R.id.fragment_login_btn_login);
        btnLogin.setOnClickListener(v -> onClickLogin(view));

        LoginButton btnFacebookLogin = (LoginButton) view.findViewById(R.id.fragment_login_login_button);
        btnFacebookLogin.setFragment(this);
        btnFacebookLogin.registerCallback(mCallbackManager, loginCallback);

        UserTracker.getInstance().addListener(this.hashCode(), UserEvent.LOGIN, () -> btnFacebookLogin.setVisibility(View.INVISIBLE));
        UserTracker.getInstance().addListener(this.hashCode(), UserEvent.LOGOUT, () -> btnFacebookLogin.setVisibility(View.VISIBLE));

		return view;
	}

    private FacebookCallback<LoginResult> loginCallback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        };

    public static LoginFragment newInstance(String title) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(StartActivity.PAGE_TITLE, title);
        fragment.setArguments(args);

        return fragment;
    }


    private void onClickLogin(View view) {
        TextView txtPhrases = (TextView) view.findViewById(R.id.login_fragment_txt_progress_phrases);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.login_fragment_progress_bar);

        new TalkingProgressBar(progressBar, txtPhrases, 25 * 1000, new String[]{
                "Initializing 8 AM lecture...",
                "Generating impossible exam questions...",
                "Brewing coffee for Bulten...",
                "Calculating derivatives..."
        } ).speak();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        UserTracker.getInstance().unsubscribe(this.hashCode());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}