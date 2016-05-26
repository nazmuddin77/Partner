package com.aasaanjobs.partner.root.di.components;

import android.app.Application;
import android.content.Context;

import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.base.data.preferences.SharedPrefService;
import com.aasaanjobs.partner.base.presenter.jobs.BasePartnerJob;
import com.aasaanjobs.partner.base.utils.PrimaryKeyFactory;
import com.aasaanjobs.partner.root.PartnerApplication;
import com.aasaanjobs.partner.root.di.customidentifiers.ApplicationContext;
import com.aasaanjobs.partner.root.di.modules.ApplicationModule;
import com.aasaanjobs.partner.root.di.modules.DataModule;
import com.aasaanjobs.partner.root.di.modules.UtilityModule;
import com.birbit.android.jobqueue.JobManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by nazmuddinmavliwala on 23/05/16.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DataModule.class,
        UtilityModule.class
})
public interface ApplicationComponent {

    @ApplicationContext
    Context provideApplcationContext();

    Application provideApplication();

    Gson provideGson();

    OkHttpClient provideOkHttpClient();

    Retrofit provideRetrofit();

    RetrofitService provideRetrofitService();

    RealmService provideRealmService();

    SharedPrefService provideSharedPrefService();

    PrimaryKeyFactory providePrimaryKeyFactory();

    JobManager provideJobManager();

    void injectApplication(PartnerApplication application);

    void injectJob(BasePartnerJob job);

}
