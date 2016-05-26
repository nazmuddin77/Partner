package com.aasaanjobs.partner.root;

import android.app.Application;

import com.aasaanjobs.partner.root.di.components.ApplicationComponent;
import com.aasaanjobs.partner.root.di.HasComponent;
import com.aasaanjobs.partner.root.di.components.DaggerApplicationComponent;
import com.aasaanjobs.partner.root.di.modules.ApplicationModule;
import com.aasaanjobs.partner.root.di.modules.DataModule;
import com.aasaanjobs.partner.root.di.modules.UtilityModule;



/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */
public class PartnerApplication extends Application implements HasComponent<ApplicationComponent> {


    private ApplicationComponent applicationComponent;

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
    }

    @Override
    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

}
