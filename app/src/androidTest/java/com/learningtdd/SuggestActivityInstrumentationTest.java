package com.learningtdd;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.learningtdd.activity.SuggestSiteActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static com.learningtdd.util.ImageViewHasDrawableMatcher.hasDrawable;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class SuggestActivityInstrumentationTest {

	@Rule
	public IntentsTestRule<SuggestSiteActivity> mIntentsRule = new IntentsTestRule<>(SuggestSiteActivity.class);

	@Before
	public void stubCameraIntent() {
		ActivityResult result = createImageStub();

		intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result);
	}

	@Test
	public void shareImageTest() {
		ViewInteraction imageView = onView(withId(R.id.activity_suggest_image));

		imageView.check(matches(not(hasDrawable())));
		imageView.perform(click());
		imageView.check(matches(hasDrawable()));
	}

	private ActivityResult createImageStub() {
		Bundle bundle = new Bundle();
		bundle.putParcelable("Test", BitmapFactory.decodeResource(mIntentsRule.getActivity().getResources(), R.drawable.bulbasaur));

		Intent result = new Intent();
		result.putExtras(bundle);

		return new ActivityResult(Activity.RESULT_OK, result);
	}
}