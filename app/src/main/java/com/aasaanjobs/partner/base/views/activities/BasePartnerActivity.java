package com.aasaanjobs.partner.base.views.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.aasaanjobs.partner.base.di.components.BaseActivityComponent;
import com.aasaanjobs.partner.base.presenter.BaseViewListener;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */
public abstract class BasePartnerActivity extends BaseActivity implements BaseViewListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
        setContentView(getActivityLayout());
        attachFragment();
    }

    @Override
    public BaseActivityComponent getBaseActivityComponent() {
        return getComponent();
    }

    private void attachFragment() {
        addFragment(getContainerViewId(),getFragment());
    }

    protected abstract void initDagger();

    protected abstract Fragment getFragment();

    protected abstract int getContainerViewId();

    protected abstract int getActivityLayout();
}
