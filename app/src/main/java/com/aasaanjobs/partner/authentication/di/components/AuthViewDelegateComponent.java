package com.aasaanjobs.partner.authentication.di.components;

import com.aasaanjobs.partner.authentication.di.modules.AuthViewDelegateModule;
import com.aasaanjobs.partner.authentication.views.viewdelegates.AuthViewDelegate;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

import dagger.Subcomponent;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
@Subcomponent(modules = {
        AuthViewDelegateModule.class
})
public interface AuthViewDelegateComponent {
    void injectViewDelegate(AuthViewDelegate viewDelegate);
}
