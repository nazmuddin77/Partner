package com.aasaanjobs.partner.authentication.views.fragments;

import com.aasaanjobs.partner.R;
import com.aasaanjobs.partner.authentication.di.modules.AuthFragmentModule;
import com.aasaanjobs.partner.authentication.presenter.AuthenticationPresenter;
import com.aasaanjobs.partner.authentication.presenter.AuthenticationViewListener;
import com.aasaanjobs.partner.authentication.views.viewinteractors.AuthDelegateInteractor;
import com.aasaanjobs.partner.authentication.views.viewdelegates.AuthViewDelegate;
import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.views.fargments.BasePartnerFragment;

import javax.inject.Inject;


/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */
public class AuthenticationFragment extends BasePartnerFragment
        implements AuthenticationViewListener, AuthDelegateInteractor {

    @Inject
    AuthenticationPresenter presenter;

    @Inject
    AuthViewDelegate viewDelegate;

    @Inject
    RealmService realmService;

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDagger() {
        getComponent().provideAuthFragmentComponent
                (new AuthFragmentModule(this)).injectFragment(this);
    }

    @Override
    protected void onViewInit() {
        presenter.fetchFA();
        presenter.fetchFADescription();
        presenter.fetchFACategory();
    }

}
