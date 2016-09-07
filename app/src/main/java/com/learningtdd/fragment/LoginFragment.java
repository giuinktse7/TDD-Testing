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
import com.learningtdd.util.TalkingProgressBar;

public class LoginFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);
        setupBtnOnLoginClick(view);


		return view;
	}

    private void setupBtnOnLoginClick(View view) {
        Button btnLogin = (Button) view.findViewById(R.id.fragment_login_btn_login);
        TextView txtPhrases = (TextView) view.findViewById(R.id.login_fragment_txt_progress_phrases);

        btnLogin.setOnClickListener(v -> {
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.login_activity_progress_bar);

            new TalkingProgressBar(progressBar, txtPhrases, 25 * 1000, new String[]{
                    "Initializing 8 AM lecture...",
                    "Generating impossible exam questions...",
                    "Brewing coffee for Bulten...",
                    "Calculating derivatives..."
            } ).speak();
        });
    }
}