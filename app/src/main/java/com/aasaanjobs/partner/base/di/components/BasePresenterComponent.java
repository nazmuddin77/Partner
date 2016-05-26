package com.aasaanjobs.partner.base.di.components;
import com.aasaanjobs.partner.authentication.di.modules.AuthPresenterModule;
import com.aasaanjobs.partner.authentication.di.components.AuthenticationPresenterComponent;
import com.aasaanjobs.partner.base.di.modules.BasePresenterModule;
import com.aasaanjobs.partner.base.presenter.BasePresenter;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

import dagger.Component;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */

@ScopedFragment
@Component(modules = {
        BasePresenterModule.class
},
dependencies = {
        BaseActivityComponent.class
})
public interface BasePresenterComponent {

    void injectBasePresenter(BasePresenter basePresenter);

    AuthenticationPresenterComponent provideAuthPresenterComponent(
            AuthPresenterModule module);
}
