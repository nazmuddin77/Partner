package com.aasaanjobs.partner.base.di.components;

import android.content.Context;

import com.aasaanjobs.partner.authentication.di.components.AuthenticationComponent;
import com.aasaanjobs.partner.authentication.di.modules.AuthenticationModule;
import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.base.data.preferences.SharedPrefService;
import com.aasaanjobs.partner.base.di.modules.BaseActivityModule;
import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.utils.LoggerUtil;
import com.aasaanjobs.partner.base.utils.PrimaryKeyFactory;
import com.aasaanjobs.partner.base.views.activities.BaseActivity;
import com.aasaanjobs.partner.root.di.components.ApplicationComponent;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityContext;
import com.aasaanjobs.partner.root.di.customidentifiers.ActivityUnBinder;
import com.aasaanjobs.partner.root.di.customscopes.ScopedActivity;
import com.birbit.android.jobqueue.JobManager;

import butterknife.Unbinder;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by nazmuddinmavliwala on 18/05/16.
 */

@ScopedActivity
@Component(modules = {
        BaseActivityModule.class
},
        dependencies = {
                ApplicationComponent.class
        })
public interface BaseActivityComponent {

    @ActivityUnBinder
    Unbinder provideUnbinder();

    @ActivityContext
    Context provideContext();

    Retrofit provideRetrofit();

    RetrofitService provideRetroFitService();

    OkHttpClient provideOkHttpClient();

    RealmService provideRealmService();

    SharedPrefService provideSharedPrefService();

    LoggerUtil provideLoggerUtil();

    PrimaryKeyFactory providePrimaryKeyFactory();

    JobManager provideJobManager();

    void injectBaseActivity(BaseActivity activity);


    AuthenticationComponent provideAuthenticationComponent(AuthenticationModule authenticationModule);
}
