package com.aasaanjobs.partner.authentication.views.fragments;

import android.support.v4.view.ViewPager;

import com.aasaanjobs.partner.R;
import com.aasaanjobs.partner.authentication.di.modules.AuthFragmentModule;
import com.aasaanjobs.partner.authentication.presenter.AuthenticationPresenter;
import com.aasaanjobs.partner.authentication.presenter.AuthenticationViewListener;
import com.aasaanjobs.partner.authentication.views.viewinteractors.AuthDelegateInteractor;
import com.aasaanjobs.partner.authentication.views.viewdelegates.AuthViewDelegate;
import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.views.fargments.BasePartnerFragment;

import javax.inject.Inject;

import butterknife.BindView;


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

    @BindView(R.id.v_auth)
    ViewPager viewPager;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_auth;
    }

    @Override
    protected void initDagger() {
        getComponent().provideAuthFragmentComponent
                (new AuthFragmentModule(this)).injectFragment(this);
    }

    @Override
    protected void onViewInit() {
    }
}
