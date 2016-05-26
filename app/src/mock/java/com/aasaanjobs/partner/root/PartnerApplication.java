package com.aasaanjobs.partner.root;

import android.app.Application;

import com.aasaanjobs.partner.root.di.components.ApplicationComponent;
import com.aasaanjobs.partner.root.di.HasComponent;
import com.aasaanjobs.partner.root.di.components.DaggerApplicationComponent;
import com.aasaanjobs.partner.root.di.modules.ApplicationModule;
import com.aasaanjobs.partner.root.di.modules.DataModule;
import com.aasaanjobs.partner.root.di.modules.UtilityModule;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;


/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */
public class PartnerApplication extends Application  {

    private static ApplicationComponent applicationComponent;

    @Inject
    Stetho.Initializer initializer;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .utilityModule(new UtilityModule())
                .build();

        applicationComponent.injectApplication(this);

        Stetho.initialize(initializer);
    }

    public static ApplicationComponent getComponent() {
        return applicationComponent;
    }

}
