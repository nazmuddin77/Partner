package com.aasaanjobs.partner.root.di.modules;

import android.app.Application;
import android.content.Context;

import com.aasaanjobs.partner.root.di.customidentifiers.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nazmuddinmavliwala on 15/04/16.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return this.application;
    }

    @Provides
    @ApplicationContext
    public Context provideApplicationContext() {
        return this.application;
    }
}
