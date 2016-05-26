package com.aasaanjobs.partner.authentication.di.modules;

import com.aasaanjobs.partner.authentication.presenter.FAService;
import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.root.di.customscopes.ScopedActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */

@ScopedActivity
@Module
public class AuthPresenterModule {

    @ScopedActivity
    @Provides
    FAService provideFAService(RetrofitService retrofitService) {
        return retrofitService.create(FAService.class);
    }

}
