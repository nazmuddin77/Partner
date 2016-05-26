package com.aasaanjobs.partner.authentication.di.components;

import com.aasaanjobs.partner.authentication.di.modules.AuthFragmentModule;
import com.aasaanjobs.partner.authentication.views.fragments.AuthenticationFragment;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

import dagger.Subcomponent;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
@Subcomponent(modules = {
        AuthFragmentModule.class
})
public interface AuthFragmentComponent {
    void injectFragment(AuthenticationFragment fragment);
}
