package com.learningtdd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Profile;
import com.learningtdd.R;
import com.learningtdd.adapter.ConversationAdapter;
import com.learningtdd.core.Conversation;
import com.learningtdd.core.Message;
import com.learningtdd.core.User;

import java.util.Arrays;

public class ConversationsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversations, container, false);

        if (Profile.getCurrentProfile() != null) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_conversations_recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new ConversationAdapter(Arrays.asList(createMockConversation(), createMockConversation())));
        }

        return view;
    }

    private Conversation createMockConversation() {
        User me = new User("Jonathan", "Krän", "k45f2");
        User other = new User("David", "Smith", "433fgj2");

        Message message = new Message(other, "Ka.");

        Conversation conversation = new Conversation(
                me,
                other);

        conversation.sendMessage(message);

        return conversation;
    }
}