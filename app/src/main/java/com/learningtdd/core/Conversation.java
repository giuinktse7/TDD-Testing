package com.learningtdd.core;

import android.util.Log;

import com.facebook.Profile;

import java.util.TreeSet;

public class Conversation {

    private TreeSet<Message> mMessages;
    private final User mFirstUser, mSecondUser;

    public Conversation(User firstUser, User secondUser) {
        mFirstUser = firstUser;
        mSecondUser = secondUser;
        mMessages = new TreeSet<>();
    }

    public static Conversation between(User firstUser, User secondUser) {
        return new Conversation(firstUser, secondUser);
    }

    public void sendMessage(Message message)  {
        if(!isValidSender(message.getSender()))
            Log.d("ERROR", String.format("User %s is not part of conversation %s", message.getSender(), this));

        mMessages.add(message);
    }

    private boolean isValidSender(User sender) {
        return sender.equals(mFirstUser) || sender.equals(mSecondUser);
    }

    public User getRecipient() {
        // TODO Check more than just first & last name,
        // TODO if they're equal for both users this is problematic.
        Profile activeUser = Profile.getCurrentProfile();

        // We should never be running this code if there is no logged in user. Fix later~
        if (activeUser == null)
            return null;

        // TODO Broken, fix the conditional and return mFirstUser if conditional is false.
        if (mFirstUser.getId().equals(activeUser.getId()))
            return mSecondUser;
        else
            return mSecondUser; // TODO Should return mFirstUser, see above
    }

    public Message getLatestMessageFrom(User user) {
        for (Message message : mMessages)
            if (message.getSender().equals(user))
                return message;

        return null;
    }
}
