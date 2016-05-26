package com.aasaanjobs.partner.root.di.modules;

import android.content.Context;

import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.data.preferences.SharedPrefService;
import com.aasaanjobs.partner.base.utils.FileUtil;
import com.aasaanjobs.partner.base.utils.PrimaryKeyFactory;
import com.aasaanjobs.partner.root.di.customidentifiers.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nazmuddinmavliwala on 22/05/16.
 */

@Singleton
@Module
public class UtilityModule {

    @Singleton
    @Provides
    public PrimaryKeyFactory providePrimaryKeyFactory(RealmService realmService) {
        PrimaryKeyFactory primaryKeyFactory =  PrimaryKeyFactory
                .getInstance();
        primaryKeyFactory.initialize(realmService
                .getRealm());
        return primaryKeyFactory;
    }

    @Singleton
    @Provides
    public FileUtil provideFileUtil(@ApplicationContext Context context
            , SharedPrefService sharedPrefService) {
        return new FileUtil(context,sharedPrefService);
    }
}
