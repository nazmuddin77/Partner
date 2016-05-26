package com.aasaanjobs.partner.root.di.modules;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.aasaanjobs.partner.base.data.db.realmadapters.RealmTypeAdapterFactory;
import com.aasaanjobs.partner.base.data.db.RealmManager;
import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.data.network.RetrofitManager;
import com.aasaanjobs.partner.base.data.network.RetrofitService;
import com.aasaanjobs.partner.base.data.preferences.SharedPrefManager;
import com.aasaanjobs.partner.base.data.preferences.SharedPrefService;
import com.aasaanjobs.partner.base.utils.FileUtil;
import com.aasaanjobs.partner.root.di.customidentifiers.ApplicationContext;
import com.aasaanjobs.partner.root.di.customidentifiers.FacebookInterceptor;
import com.aasaanjobs.partner.root.di.customidentifiers.HttpInterceptor;
import com.aasaanjobs.partner.root.services.PartnerGCMJobService;
import com.aasaanjobs.partner.root.services.PartnerJobService;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.di.DependencyInjector;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmObject;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */

@Singleton
@Module
public class DataModule {

    private static final String API_BASE_URL = "http://api1.aasaanjobs.com/";

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapterFactory(new RealmTypeAdapterFactory())
                .create() ;
    }

    @Singleton
    @Provides
    @FacebookInterceptor
    public Interceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Singleton
    @HttpInterceptor
    @Provides
    public Interceptor provideHttpInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(@HttpInterceptor Interceptor httpLoggingInterceptor
            , @FacebookInterceptor Interceptor stethoInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request customRequest = originalRequest.newBuilder()
                        .header("Content-type", "Application/JSON")
                        .build();
                return chain.proceed(customRequest);
            }
        });
        httpClient.addInterceptor(httpLoggingInterceptor);
        httpClient.addInterceptor(stethoInterceptor);
        return httpClient.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public RetrofitService provideRetrofitService(Retrofit retrofit) {
        return RetrofitManager.getInstance(retrofit);
    }

    @Singleton
    @Provides
    public RealmInspectorModulesProvider provideInspectorModule(
            @ApplicationContext Context context) {
        return RealmInspectorModulesProvider.builder(context)
                .withMetaTables()
                .databaseNamePattern(Pattern.compile(".+\\.realm"))
                .build();
    }


    @Singleton
    @Provides
    public Stetho.Initializer provideSeetheInitializer(
            @ApplicationContext Context context
            , RealmInspectorModulesProvider provider) {
        return  Stetho.newInitializerBuilder(context)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                .enableWebKitInspector(provider)
                .build();
    }

    @Singleton
    @Provides
    public RealmService provideRealmService(@ApplicationContext Context context, FileUtil fileUtil) {
        return new RealmManager(context,fileUtil);
    }

    @Singleton
    @Provides
    public SharedPrefService provideSharedPrefManager(
            @ApplicationContext Context context) {
        return new SharedPrefManager(context);
    }

    @Singleton
    @Provides
    public JobManager provideJobManager(@ApplicationContext Context context) {
        Configuration.Builder builder = new Configuration.Builder(context)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";
                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }

                })
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120);//wait 2 minute
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.scheduler(FrameworkJobSchedulerService.createSchedulerFor(context,
                    PartnerJobService.class), false);
        }
        return new JobManager(builder.build());

    }

}
