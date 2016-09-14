package com.learningtdd.facebookUtil.userEvent;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that enables listening to logout / login through Facebook
 */
public class UserTracker {
    private static UserTracker mInstance;

    private Map<Integer, ListenerGroup> mListenerGroups;
    private AccessTokenTracker mTokenTracker;

    public UserTracker() {
        mListenerGroups = new HashMap<>();
    }

    public static UserTracker getInstance() {
        if (mInstance == null)
            mInstance = new UserTracker();

        return mInstance;
    }

    public void startTracking() {
        if (mTokenTracker == null)
            mTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    boolean isLogout = oldAccessToken != null && currentAccessToken == null;
                    if (isLogout)
                        notifyListeners(UserEvent.LOGOUT);
                    else
                        notifyListeners(UserEvent.LOGIN);
                }
            };
        mTokenTracker.stopTracking();
        mTokenTracker.startTracking();
    }

    public void addListener(int fragmentId, UserEvent event, UserEventListener listener) {
        if (mListenerGroups.get(fragmentId) == null)
            mListenerGroups.put(fragmentId, new ListenerGroup());

        mListenerGroups.get(fragmentId).addListener(event, listener);
    }

    private void notifyListeners(UserEvent event) {
        for (ListenerGroup group : mListenerGroups.values())
            group.notifyListeners(event);
    }

    public void unsubscribe(int fragmentId) {
        mListenerGroups.remove(fragmentId);
    }

    //Holds listeners from a Fragment.
    private static class ListenerGroup {
        private Map<UserEvent, List<UserEventListener>> mListeners;

        public ListenerGroup() {
            mListeners = new HashMap<>();

            for (UserEvent event : UserEvent.values())
                mListeners.put(event, new ArrayList<>());
        }

        public void addListener(UserEvent event, UserEventListener listener) {
            mListeners.get(event).add(listener);
            Log.d("ADDED", "Added listener.");
        }

        public void notifyListeners(UserEvent event) {
            for (UserEventListener listener : mListeners.get(event))
                listener.onEvent();
        }
    }
}
