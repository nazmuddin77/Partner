package com.aasaanjobs.partner.authentication.di.modules;

import android.view.View;

import com.aasaanjobs.partner.authentication.presenter.AuthenticationViewListener;
import com.aasaanjobs.partner.authentication.views.viewinteractors.AuthDelegateInteractor;
import com.aasaanjobs.partner.authentication.views.fragments.AuthenticationFragment;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
@Module
public class AuthFragmentModule {

    private final AuthenticationFragment fragment;

    public AuthFragmentModule(AuthenticationFragment fragment) {
        this.fragment = fragment;
    }

    @ScopedFragment
    @Provides
    public View provideView() {
        return this.fragment.getView();
    }

    @ScopedFragment
    @Provides
    public AuthDelegateInteractor getDelegateInteractor() {
        return this.fragment;
    }

    @ScopedFragment
    @Provides
    public AuthenticationViewListener getViewListener() {
        return this.fragment;
    }


}
