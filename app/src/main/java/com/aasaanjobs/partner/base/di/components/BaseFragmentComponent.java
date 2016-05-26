package com.aasaanjobs.partner.base.di.components;

import com.aasaanjobs.partner.authentication.di.components.AuthFragmentComponent;
import com.aasaanjobs.partner.authentication.di.modules.AuthFragmentModule;
import com.aasaanjobs.partner.base.di.modules.BaseFragmentModule;
import com.aasaanjobs.partner.base.presenter.BasePresenter;
import com.aasaanjobs.partner.base.views.fargments.BaseFragment;
import com.aasaanjobs.partner.root.di.customidentifiers.FragmentUnBinder;
import com.aasaanjobs.partner.root.di.customscopes.ScopedFragment;

import butterknife.Unbinder;
import dagger.Component;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedFragment
@Component(modules = {
        BaseFragmentModule.class
},
dependencies = {
        BaseActivityComponent.class
})
public interface BaseFragmentComponent {

    @FragmentUnBinder
    Unbinder provideUnbinder();

    void injectBaseFragment(BaseFragment baseFragment);

    AuthFragmentComponent provideAuthFragmentComponent(AuthFragmentModule module);
}
