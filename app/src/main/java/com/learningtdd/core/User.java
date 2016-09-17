package com.learningtdd.core;

import com.facebook.Profile;

public class User {

    private String mFirstName;
    private String mLastName;
    private String mId;

    public User(String firstName, String lastName, String id) {
        mFirstName = firstName;
        mLastName = lastName;
        mId = id;
    }

    public User(Profile profile) {
        mFirstName = profile.getFirstName();
        mLastName = profile.getLastName();
        mId = profile.getId();
    }

    public String getFirstName() { return mFirstName; }
    public String getLastName() { return mLastName; }
    public String getId() { return mId; }
}
