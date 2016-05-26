package com.aasaanjobs.partner.authentication.di.components;

import com.aasaanjobs.partner.authentication.di.modules.AuthPresenterModule;
import com.aasaanjobs.partner.authentication.presenter.AuthenticationPresenter;
import com.aasaanjobs.partner.authentication.presenter.FAService;
import com.aasaanjobs.partner.root.di.customscopes.ScopedActivity;
import dagger.Subcomponent;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */

@ScopedActivity
@Subcomponent(modules = {
        AuthPresenterModule.class
})
public interface AuthenticationPresenterComponent {

    FAService provideFAService();

    void injectPresenter(AuthenticationPresenter presenter);
}
