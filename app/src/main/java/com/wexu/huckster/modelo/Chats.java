package com.wexu.huckster.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Juliana on 2/12/2016.
 */

public class Chats {

    private static Chats repository = new Chats();
    private HashMap<String, Chat> chats = new HashMap<>();

    public static Chats getInstance() {
        return repository;
    }

    private Chats() {
        saveLead(new Chat("Alexander Pierrot"));
        saveLead(new Chat("Juliana"));
        saveLead(new Chat("Esteban"));
        saveLead(new Chat("Andrés"));
        saveLead(new Chat("Sebastián"));
        saveLead(new Chat("Paula"));
        saveLead(new Chat("Machado"));
        saveLead(new Chat("Nicolas"));
        saveLead(new Chat("Willy"));
        saveLead(new Chat("Ricardo"));
        saveLead(new Chat("Isabel"));
        saveLead(new Chat("Madrid"));

    }

    private void saveLead(Chat c) {
        chats.put(c.getUserName(), c);
    }

    public List<Chat> getLeads() {
        return new ArrayList<>(chats.values());
    }
}
