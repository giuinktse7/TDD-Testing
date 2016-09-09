package com.learningtdd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.learningtdd.R;
import com.learningtdd.activity.StartActivity;
import com.learningtdd.util.TalkingProgressBar;

public class LoginFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button btnLogin = (Button) view.findViewById(R.id.fragment_login_btn_login);
        btnLogin.setOnClickListener(v -> onClickLogin(view));

		return view;
	}

    public static LoginFragment newInstance(String title) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(StartActivity.PAGE_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    private void onClickLogin(View view) {
        TextView txtPhrases = (TextView) view.findViewById(R.id.login_fragment_txt_progress_phrases);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.login_activity_progress_bar);

        new TalkingProgressBar(progressBar, txtPhrases, 25 * 1000, new String[]{
                "Initializing 8 AM lecture...",
                "Generating impossible exam questions...",
                "Brewing coffee for Bulten...",
                "Calculating derivatives..."
        } ).speak();
    }
}