package com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public class ItemMovedCommand extends AbstractAdapterCommand {


    private final int fromPosition;
    private final int toPosition;

    public ItemMovedCommand(int fromPosition, int toPosition) {
        if (fromPosition < 0) {
            throw new IllegalArgumentException("fromPosition < 0");
        }
        if (toPosition < 0) {
            throw new IllegalArgumentException("toPosition < 0");
        }

        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    @MainThread
    @Override
    public void execute(@NonNull RecyclerView.Adapter<?> adapter) {
        adapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemMovedCommand that = (ItemMovedCommand) o;

        if (fromPosition != that.fromPosition) {
            return false;
        }
        return toPosition == that.toPosition;
    }

}
