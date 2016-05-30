package com.aasaanjobs.partner.base.views.adapters.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterdelegate.AdapterDelegate;
import com.aasaanjobs.partner.base.views.adapters.recyclerview.adapterdelegate.AdapterDelegateManager;

import java.util.List;

/**
 * Created by nazmuddinmavliwala on 26/12/15.
 */
public abstract class BasePartnerRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    public final Context context;
    public List<T> items;
    public AdapterDelegateManager<List<T>> delegatesManager;

    public BasePartnerRecyclerViewAdapter(Context context, List<T> items) {
        this.items = items;
        this.context = context;
        this.delegatesManager = new AdapterDelegateManager<>();
        addDelegates();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(items, position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    private void addDelegates() {
        List<AdapterDelegate<T>> delegates = getAdapterDelegates();
        int i = 0;
        while (i < delegates.size()) {
            addDelegate(delegates.get(i));
            i++;
        }
    }


    public void addDelegate(AdapterDelegate delegate) {
        delegatesManager.addDelegate(delegate);
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    protected boolean isValidPosition(int position) {
        return position >= 0;
    }

    protected abstract List<AdapterDelegate<T>> getAdapterDelegates();
}
