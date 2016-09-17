package com.learningtdd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.learningtdd.R;
import com.learningtdd.adapter.ConversationAdapter;
import com.learningtdd.core.Conversation;
import com.learningtdd.core.Message;
import com.learningtdd.core.User;

import java.util.Arrays;

public class OptionsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_options, container, false);

        TextView txtLogout = (TextView) view.findViewById(R.id.fragment_options_logout);
        txtLogout.setOnClickListener(currentView -> logout());

        return view;
    }

    private void logout() {
        LoginManager.getInstance().logOut();
    }
}