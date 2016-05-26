package com.aasaanjobs.partner.authentication.views.activities;

import android.support.v4.app.Fragment;

import com.aasaanjobs.partner.R;
import com.aasaanjobs.partner.authentication.di.modules.AuthenticationModule;
import com.aasaanjobs.partner.authentication.views.fragments.AuthenticationFragment;
import com.aasaanjobs.partner.base.views.activities.BasePartnerActivity;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */
public class AuthenticationActivity extends BasePartnerActivity {

    @Override
    protected void initDagger() {
        getComponent().provideAuthenticationComponent
                (new AuthenticationModule()).injectActivity(this);
    }

    @Override
    protected Fragment getFragment() {
        return new AuthenticationFragment();
    }

    @Override
    protected int getContainerViewId() {
        return R.id.fl_fragment;
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

}
