package com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public class ItemChangedCommand extends AbstractAdapterCommand {

    private final int position;

    public ItemChangedCommand(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("position < 0");
        }
        this.position = position;
    }

    @MainThread
    @Override
    public void execute(@NonNull RecyclerView.Adapter<?> adapter) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        ItemChangedCommand that = (ItemChangedCommand) o;
        return position == that.position;
    }

}
