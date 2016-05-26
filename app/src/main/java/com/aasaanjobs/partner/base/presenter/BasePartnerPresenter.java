package com.aasaanjobs.partner.base.presenter;

import android.content.Context;

import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */

@ScopedFragment
public abstract class BasePartnerPresenter extends BasePresenter {

    public BasePartnerPresenter(@ActivityContext Context context
            , BaseViewListener viewListener) {
        super(context, viewListener);
        initDagger();
    }

    protected <T extends BaseViewListener>T getViewListener(Class<T> clazz) {
        return (T) viewListener;
    }

    protected abstract void initDagger();
}
