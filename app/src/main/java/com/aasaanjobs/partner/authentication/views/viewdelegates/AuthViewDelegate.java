package com.aasaanjobs.partner.authentication.views.viewdelegates;

import android.content.Context;
import android.view.View;

import com.aasaanjobs.partner.authentication.di.modules.AuthViewDelegateModule;
import com.aasaanjobs.partner.authentication.views.viewinteractors.AuthDelegateInteractor;
import com.aasaanjobs.partner.base.views.viewdelegates.BasePartnerViewDelegate;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

import javax.inject.Inject;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
public class AuthViewDelegate extends BasePartnerViewDelegate {

    @Inject
    public AuthViewDelegate(@ActivityContext Context context, View view
            , AuthDelegateInteractor interactor) {
        super(context, view, interactor);
    }

    @Override
    protected void initDagger() {
        getComponent().provideViewDelegateComponent
                (new AuthViewDelegateModule()).injectViewDelegate(this);
    }
}
