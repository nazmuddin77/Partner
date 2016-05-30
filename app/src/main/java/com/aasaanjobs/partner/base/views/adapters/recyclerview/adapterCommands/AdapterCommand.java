package com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */

public interface AdapterCommand {

    @MainThread
    void execute(@NonNull RecyclerView.Adapter<?> adapter);
}
