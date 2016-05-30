package com.aasaanjobs.partner.base.views.adapters.recyclerview;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands.AdapterCommand;

import java.util.List;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public class AdapterCommandProcessor {

    private final RecyclerView.Adapter adapter;

    public AdapterCommandProcessor(@NonNull RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @MainThread
    public void execute(@NonNull  List<AdapterCommand> commands) {
        int i = 0;
        while (i < commands.size()) {
            commands.get(i).execute(adapter);
            i++;
        }
    }
}
