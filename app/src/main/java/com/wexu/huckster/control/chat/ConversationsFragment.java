/**
 * Vista para los leads del CRM
 */
package com.wexu.huckster.control.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wexu.huckster.R;
import com.wexu.huckster.modelo.Chat;
import com.wexu.huckster.modelo.Chats;

public class ConversationsFragment extends Fragment {

    private ListView chatsList;
    private ConversationsAdapter chatsAdapter;

    public ConversationsFragment() {
        // Required empty public constructor
    }

    public static ConversationsFragment newInstance(/*parámetros*/) {
        ConversationsFragment fragment = new ConversationsFragment();
        // Setup parámetros
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Gets parámetros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_conversations, container, false);

        // Instancia del ListView.
        chatsList = (ListView) root.findViewById(R.id.leads_list);

        // Inicializar el adaptador con la fuente de datos.
        chatsAdapter = new ConversationsAdapter(getActivity(), Chats.getInstance().getLeads());

        //Relacionando la lista con el adaptador
        chatsList.setAdapter(chatsAdapter);

        chatsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Chat currentLead = chatsAdapter.getItem(position);
                Toast.makeText(getActivity(),
                        "Iniciar screen de detalle para: \n" + currentLead.getUserName(),
                        Toast.LENGTH_SHORT).show();
                Intent i= new Intent(getActivity(), ChatActivity.class);
                i.putExtra("NOMBRE_PERSONA", currentLead.getUserName());
                startActivity(i);

            }
        });

        return root;
    }
}