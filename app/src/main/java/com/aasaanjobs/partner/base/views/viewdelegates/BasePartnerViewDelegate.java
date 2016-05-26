package com.aasaanjobs.partner.base.views.viewdelegates;

import android.content.Context;
import android.view.View;

import com.aasaanjobs.partner.base.views.viewinteractors.BaseDelegateInteractor;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
public abstract class BasePartnerViewDelegate extends BaseViewDelegate {

    public BasePartnerViewDelegate(@ActivityContext Context context, View view
            , BaseDelegateInteractor interactor) {
        super(context, view, interactor);
        initDagger();
    }

    protected <T extends BaseDelegateInteractor>T getDelegateInteractor(Class<T> clazz) {
        if(interactor != null) {
            return (T)interactor;
        } else {
            throw new NullPointerException();
        }
    }

    protected abstract void initDagger();

}
