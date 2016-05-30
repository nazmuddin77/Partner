package com.aasaanjobs.partner.base.views.adapters.recyclerview;

/**
 * Created by nazmuddinmavliwala on 27/05/16.
 */
public interface ItemChangeDetector<T> {
    boolean hasChanged(T oldItem, T newItem);
}
