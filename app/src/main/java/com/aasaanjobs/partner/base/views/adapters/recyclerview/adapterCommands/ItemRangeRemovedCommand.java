package com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public class ItemRangeRemovedCommand extends AbstractAdapterCommand {

    private final int startPosition;
    private final int itemCount;

    public ItemRangeRemovedCommand(int startPosition, int itemCount) {
        if (startPosition < 0) {
            throw new IllegalArgumentException("startPosition < 0");
        }

        if (itemCount <= 0){
            throw new IllegalArgumentException("itemCount <= 0");
        }

        this.startPosition = startPosition;
        this.itemCount = itemCount;
    }

    @MainThread
    @Override
    public void execute(@NonNull RecyclerView.Adapter<?> adapter) {
        adapter.notifyItemRangeRemoved(startPosition, itemCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemRangeRemovedCommand that = (ItemRangeRemovedCommand) o;

        if (startPosition != that.startPosition) {
            return false;
        }
        return itemCount == that.itemCount;
    }

}
