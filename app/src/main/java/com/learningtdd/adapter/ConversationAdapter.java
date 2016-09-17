package com.learningtdd.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.learningtdd.R;
import com.learningtdd.core.Conversation;
import com.learningtdd.core.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationHolder> {

    private List<Conversation> mConversations;

    public ConversationAdapter() {
        mConversations = new ArrayList<>();
    }

    public ConversationAdapter(List<Conversation> conversations) {
        mConversations = conversations;
    }

    @Override
    public ConversationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_conversation_row, parent, false);

        return new ConversationHolder(view);
    }

    @Override
    public void onBindViewHolder(ConversationHolder holder, int position) {
        Log.d("RecyclerView", "Binding...");
        holder.bindConversation(mConversations.get(position));
    }

    @Override
    public int getItemCount() {
        return mConversations.size();
    }

    public static class ConversationHolder extends RecyclerView.ViewHolder {
        ImageView mProfileImage;
        TextView mName;
        TextView mLatestMessage;
        TextView mSentAt;

        public ConversationHolder(View view) {
            super(view);

            //mProfileImage = view.findViewById(R.id.)
            mName = (TextView) view.findViewById(R.id.conversation_name);
            mLatestMessage = (TextView) view.findViewById(R.id.conversation_latest_message);
            mSentAt = (TextView) view.findViewById(R.id.conversation_time_of_message);
        }

        public void bindConversation(Conversation conversation) {
            //mProfileImage.setImageDrawable(Resources.getSystem().getDrawable(R.drawable.bulbasaur));
            Log.d("RecyclerView", "Hallooo..");
            if (conversation.getRecipient() != null) {
                mName.setText(conversation.getRecipient().getFirstName());

                Message latestMessage = conversation.getLatestMessageFrom(conversation.getRecipient());
                if (latestMessage != null) {
                    mLatestMessage.setText(latestMessage.getText());
                    mSentAt.setText(formatDateOfMessage(latestMessage));
                }
            }
        }

        private String getTimeFromDate(Date date) {
            SimpleDateFormat f = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return f.format(date);
        }

        private String getDayAndMonthFromDate(Date date) {
            SimpleDateFormat f = new SimpleDateFormat("MMM dd", Locale.getDefault());
            return f.format(date);
        }

        private String formatDateOfMessage(Message message) {
            return message.isFromToday() ?
                    getTimeFromDate(message.getDate()) :
                    getDayAndMonthFromDate(message.getDate());
        }
    }
}
