package com.aasaanjobs.partner.base.views.adapters.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands.AdapterCommand;
import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands.EntireDataSetChangedCommand;
import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands.ItemChangedCommand;
import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands.ItemInsertedCommand;
import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands.ItemRangeInsertedCommand;
import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands.ItemRangeRemovedCommand;
import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands.ItemRemovedCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public class DiffCommandCalculator<T> {

    private final boolean itemRangeInsertedOnFirstDiff;
    private final ItemChangeDetector<T> detector;
    private ArrayList<T> oldList;

    public DiffCommandCalculator() {
        this(false, null);
    }

    public DiffCommandCalculator(boolean itemRangeInsertedOnFirstDiff) {
        this(itemRangeInsertedOnFirstDiff, null);
    }

    public DiffCommandCalculator(ItemChangeDetector<T> detector) {
        this(false, detector);
    }

    public DiffCommandCalculator(boolean itemRangeInsertedOnFirstDiff,
                                  @Nullable ItemChangeDetector<T> detector) {
        this.itemRangeInsertedOnFirstDiff = itemRangeInsertedOnFirstDiff;
        this.detector = detector;
    }

    public List<AdapterCommand> diff(@NonNull List<T> newList) {
        int newSize = newList.size();
        // first time called
        if (oldList == null) {
            oldList = new ArrayList<>();
            oldList.addAll(newList);

            List<AdapterCommand> commands = new ArrayList<>(1);

            if (newSize == 0 || !itemRangeInsertedOnFirstDiff) {
                commands.add(new EntireDataSetChangedCommand());
            } else {
                commands.add(new ItemRangeInsertedCommand(0, newSize));
            }
            return commands;
        }

        // new list empty
        if (newList.isEmpty()) {
            if (oldList.isEmpty()){
                return Collections.emptyList();
            }
            List<AdapterCommand> commands = new ArrayList<AdapterCommand>(1);
            commands.add(new ItemRangeRemovedCommand(0, oldList.size()));
            oldList.clear(); // for next call
            return commands;
        }

        List<AdapterCommand> commands = new ArrayList<>(newSize);

        int M = oldList.size();
        int N = newList.size();

        // opt[i][j] = length of LCS of oldList[i..M] and y[j..N]
        int[][] opt = new int[M + 1][N + 1];

        // compute length of LCS and all subproblems via dynamic programming
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (oldList.get(i).equals(newList.get(j))) {
                    opt[i][j] = opt[i + 1][j + 1] + 1;
                } else {
                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
                }
            }
        }

        int insertRemoveOffset = 0;
        // recover LCS itself and print out non-matching lines to standard output
        int i = 0, j = 0;
        while (i < M && j < N) {
            T oldItem = oldList.get(i);
            T newItem = newList.get(j);
            if (oldItem.equals(newItem)) {
                if (detector != null && detector.hasChanged(oldItem, newItem)) {
                    commands.add(new ItemChangedCommand(j));
                }
                i++;
                j++;
            } else if (opt[i + 1][j] >= opt[i][j + 1]) {
                //T item = oldList.get(i);
                //handleRemoveCommand(item, i + insertRemoveOffset, insertCommands, removeCommands, commands);
                commands.add(new ItemRemovedCommand(i + insertRemoveOffset));
                insertRemoveOffset--;
                i++;
            } else {
                //T item = newList.get(j);
                //handleInsertCommand(item, j, insertCommands, removeCommands, commands);
                commands.add(new ItemInsertedCommand(j));
                insertRemoveOffset++;
                j++;
            }
        }

        // dump out one remainder of one string if the other is exhausted
        while (i < M || j < N) {
            if (i == M) {
                // T item = newList.get(j);
                // handleInsertCommand(item, j, insertCommands, removeCommands, commands);
                commands.add(new ItemInsertedCommand(j));
                insertRemoveOffset++;
                j++;
            } else if (j == N) {
                // T item = oldList.get(i);
                // handleRemoveCommand(item, i + insertRemoveOffset, insertCommands, removeCommands, commands);
                commands.add(new ItemRemovedCommand(i + insertRemoveOffset));
                insertRemoveOffset--;
                i++;
            }
        }

        oldList.clear();
        oldList.addAll(newList);
        return commands;
    }
}
