package com.aasaanjobs.partner.base.di.components;
import com.aasaanjobs.partner.authentication.di.components.AuthViewDelegateComponent;
import com.aasaanjobs.partner.authentication.di.modules.AuthViewDelegateModule;
import com.aasaanjobs.partner.base.di.modules.BaseViewDelegateModule;
import com.aasaanjobs.partner.base.views.viewdelegates.BaseViewDelegate;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;
import com.aasaanjobs.partner.root.di.customidentifiers.ViewDelegateUnBinder;

import butterknife.Unbinder;
import dagger.Component;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
@Component(modules = {
        BaseViewDelegateModule.class
},
dependencies = {
        BaseActivityComponent.class
})
public interface BaseViewDelegateComponent {

    @ViewDelegateUnBinder
    Unbinder provideUnBinder();

    void injectBaseViewDelegate(BaseViewDelegate viewDelegate);

    AuthViewDelegateComponent provideViewDelegateComponent(
            AuthViewDelegateModule module);
}
