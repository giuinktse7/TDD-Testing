package com.learningtdd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.learningtdd.R;

public class MainActivity extends AppCompatActivity {

	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initialize();
	}

	private void initialize() {
		button = (Button) findViewById(R.id.main_activity_btn);
		button.setOnClickListener(v ->
				startActivity(new Intent(MainActivity.this, SuggestSiteActivity.class)));
	}
}
