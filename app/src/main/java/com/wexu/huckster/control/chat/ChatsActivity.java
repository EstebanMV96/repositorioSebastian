package com.wexu.huckster.control.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wexu.huckster.R;

public class ChatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ConversationsFragment leadsFragment = (ConversationsFragment)
                getSupportFragmentManager().findFragmentById(R.id.leads_container);

        if (leadsFragment == null) {
            leadsFragment = ConversationsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.leads_container, leadsFragment)
                    .commit();
        }
    }
}
