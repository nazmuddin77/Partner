package com.aasaanjobs.partner.root.di.modules;

import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.utils.PrimaryKeyFactory;

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
        PrimaryKeyFactory primaryKeyFactory =  PrimaryKeyFactory.getInstance();
        primaryKeyFactory.initialize(realmService.getRealm());
        return primaryKeyFactory;
    }
}
