package com.aasaanjobs.partner.base.views.fargments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aasaanjobs.partner.base.di.components.BaseActivityComponent;
import com.aasaanjobs.partner.base.di.components.BaseFragmentComponent;
import com.aasaanjobs.partner.base.di.components.DaggerBaseFragmentComponent;
import com.aasaanjobs.partner.base.di.modules.BaseFragmentModule;
import com.aasaanjobs.partner.base.utils.LoggerUtil;
import com.aasaanjobs.partner.base.views.activities.BaseActivity;
import com.aasaanjobs.partner.root.di.customidentifiers.FragmentUnBinder;
import com.aasaanjobs.partner.root.di.HasComponent;

import javax.inject.Inject;

import butterknife.Unbinder;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */
public abstract class BaseFragment extends Fragment implements HasComponent<BaseFragmentComponent> {

    private BaseFragmentComponent baseFragmentComponent;

    @Inject
    @FragmentUnBinder
    protected Unbinder unbinder;

    @Inject
    LoggerUtil loggerUtil;


    private BaseActivityComponent getBaseActivityComponent() {
        return ((BaseActivity)getActivity()).getComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = getFragmentView(inflater,container,savedInstanceState);

        baseFragmentComponent = DaggerBaseFragmentComponent.builder()
                .baseActivityComponent(getBaseActivityComponent())
                .baseFragmentModule(new BaseFragmentModule(this,view))
                .build();

        baseFragmentComponent.injectBaseFragment(this);
        loggerUtil.setTag(setTag());
        return view;
    }

    protected String setTag() {
        return this.getClass().getName();
    }

    @Override
    public BaseFragmentComponent getComponent() {
        return baseFragmentComponent;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected abstract View getFragmentView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState);
}
