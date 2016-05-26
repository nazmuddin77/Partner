package com.aasaanjobs.partner.base.di.modules;

import android.view.View;

import com.aasaanjobs.partner.base.views.fargments.BaseFragment;
import com.aasaanjobs.partner.root.di.customidentifiers.FragmentUnBinder;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
@Module
public class BaseFragmentModule {

    private final BaseFragment baseFragment;
    private final View view;

    public BaseFragmentModule(BaseFragment baseFragment, View view) {
        this.baseFragment = baseFragment;
        this.view = view;
    }

    @ScopedFragment
    @Provides
    @FragmentUnBinder
    public Unbinder getUnBinder() {
        return ButterKnife.bind(baseFragment,view);
    }

}
