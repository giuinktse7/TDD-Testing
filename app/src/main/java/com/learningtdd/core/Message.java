package com.learningtdd.core;

import java.util.Calendar;
import java.util.Date;

public class Message implements Comparable {

    private final User mSender;
    private final String mText;
    private final Date mDateSent;

    public Message(User sender, String text) {
        mSender = sender;
        mText = text;
        mDateSent = new Date();
    }

    public User getSender() { return mSender; }

    public String getText() { return mText; }

    public Date getDate() { return mDateSent; }

    public boolean isFromToday() {
        Date now = new Date();

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(now);
        calendar2.setTime(getDate());
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    @Override
    public int compareTo(Object object) {
        return mDateSent.compareTo(((Message) object).getDate());
    }
}
