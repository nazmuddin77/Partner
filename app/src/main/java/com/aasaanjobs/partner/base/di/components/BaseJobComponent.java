package com.aasaanjobs.partner.base.di.components;

import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.base.di.modules.BaseJobModule;
import com.aasaanjobs.partner.base.presenter.jobs.BasePartnerJob;
import com.aasaanjobs.partner.root.di.components.ApplicationComponent;
import com.aasaanjobs.partner.root.di.customscopes.ScopedApplication;

import java.io.Serializable;

import dagger.Component;

/**
 * Created by nazmuddinmavliwala on 26/05/16.
 */

@ScopedApplication
@Component(modules = {
        BaseJobModule.class
},
dependencies = {
        ApplicationComponent.class
})
public interface BaseJobComponent extends Serializable {
    void injectJob(BasePartnerJob job);
}
