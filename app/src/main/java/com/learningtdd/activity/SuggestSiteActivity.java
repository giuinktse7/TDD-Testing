package com.learningtdd.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.learningtdd.R;

public class SuggestSiteActivity extends AppCompatActivity {

	private ImageView mSiteImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suggest_site);

		mSiteImage = (ImageView) findViewById(R.id.activity_suggest_image);
		mSiteImage.setOnClickListener(v -> {
			mSiteImage.setImageResource(R.drawable.bulbasaur);
		});
	}
}
