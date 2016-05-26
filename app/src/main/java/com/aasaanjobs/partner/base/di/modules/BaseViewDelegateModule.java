package com.aasaanjobs.partner.base.di.modules;
import android.view.View;

import com.aasaanjobs.partner.base.views.viewdelegates.BaseViewDelegate;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;
import com.aasaanjobs.partner.root.di.customidentifiers.ViewDelegateUnBinder;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
@Module
public class BaseViewDelegateModule {

    private final BaseViewDelegate viewDelegate;
    private final View view;

    public BaseViewDelegateModule(BaseViewDelegate viewDelegate, View view) {
        this.viewDelegate = viewDelegate;
        this.view = view;
    }

    @ScopedFragment
    @Provides
    @ViewDelegateUnBinder
    public Unbinder getUnBinder() {
        return ButterKnife.bind(viewDelegate,view);
    }
}
