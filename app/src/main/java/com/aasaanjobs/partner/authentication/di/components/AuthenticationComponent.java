package com.aasaanjobs.partner.authentication.di.components;

import com.aasaanjobs.partner.authentication.di.modules.AuthenticationModule;
import com.aasaanjobs.partner.authentication.views.activities.AuthenticationActivity;
import com.aasaanjobs.partner.root.di.customscopes.ScopedActivity;

import dagger.Subcomponent;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */

@ScopedActivity
@Subcomponent(modules = {
        AuthenticationModule.class
})
public interface AuthenticationComponent {
    void injectActivity(AuthenticationActivity activity);
}
