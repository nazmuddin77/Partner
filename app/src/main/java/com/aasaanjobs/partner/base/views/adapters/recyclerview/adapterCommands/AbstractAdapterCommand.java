package com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterCommands;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public abstract class AbstractAdapterCommand implements AdapterCommand {

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
