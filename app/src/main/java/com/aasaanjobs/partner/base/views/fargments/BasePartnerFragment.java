package com.aasaanjobs.partner.base.views.fargments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aasaanjobs.partner.base.di.components.BaseActivityComponent;
import com.aasaanjobs.partner.base.presenter.BaseViewListener;
import com.aasaanjobs.partner.base.views.activities.BaseActivity;
import com.aasaanjobs.partner.base.views.viewinteractors.BaseDelegateInteractor;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */
public abstract class BasePartnerFragment extends BaseFragment implements BaseViewListener, BaseDelegateInteractor {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDagger();
        onViewInit();
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public BaseActivityComponent getBaseActivityComponent() {
        return ((BaseActivity)getActivity()).getComponent();
    }

    protected abstract int getFragmentLayout();

    protected abstract void initDagger();


    protected void onViewInit() {}
}
