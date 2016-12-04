package com.wexu.huckster.control.chat;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;

import com.wexu.huckster.R;
import com.wexu.huckster.modelo.ChatMessage;


public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";

    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private FloatingActionButton buttonSend;
    private boolean side = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        String name;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name= null;
            } else {
                name= extras.getString("NOMBRE_PERSONA");
            }
        } else {
            name= (String) savedInstanceState.getSerializable("NOMBRE_PERSONA");
        }
        System.out.println("CONVERSANDO CON: "+name );

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Configuración de la barra de herramientas (toolbar ) como la Barra de acciones (ActionBar)
        // con la llamada de setSupportActionBar ()
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name);

        buttonSend = (FloatingActionButton) findViewById(R.id.send);

        listView = (ListView) findViewById(R.id.msgview);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.msg);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
    }

    private boolean sendChatMessage() {
        chatArrayAdapter.add(new ChatMessage(side, chatText.getText().toString()));
        chatText.setText("");
        side = !side;
        return true;
    }
}
