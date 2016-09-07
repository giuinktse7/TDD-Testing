package com.learningtdd.util;

import android.os.AsyncTask;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TalkingProgressBar extends AsyncTask<Void, Integer, Void> {

    private ProgressBarAnimation mAnimation;
    private List<String> mPhrases = new ArrayList<>();
    private long mTimePerPhrase;
    private int mMax;
    private TextView mTextView;

    public TalkingProgressBar(ProgressBar progressBar, TextView textView, long fullDuration, String[] phrases) {
        mTextView = textView;

        mPhrases.addAll(Arrays.asList(phrases));

        mMax = progressBar.getMax();
        mAnimation = new ProgressBarAnimation(progressBar, fullDuration);
        mTimePerPhrase = mAnimation.getFullDuration() / phrases.length;
    }

    public void addPhrase(String phrase) {
        mPhrases.add(phrase);
    }

    public void speak() {
        mAnimation.setProgress(mMax);
        execute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (mAnimation.barIsFull())
            return null;

        mTimePerPhrase = mAnimation.getFullDuration() / mPhrases.size();
        for (int i = 0; i < mPhrases.size(); ++i) {
            try {
            publishProgress(i, (int)mTimePerPhrase / 2);
                Thread.sleep(mTimePerPhrase);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //int phraseIndex = values[0];
        int phraseIndex = new Random().nextInt(mPhrases.size());
        int duration = Math.min(1200, values[1] / 2);

        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);

        fadeIn.setDuration(duration);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(duration);
        fadeOut.setFillAfter(true);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTextView.setText(mPhrases.get(phraseIndex));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTextView.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mTextView.startAnimation(fadeOut);
    }

    @Override
    protected void onPostExecute(Void result) {
        mTextView.setText("Login successful!");
    }


}
