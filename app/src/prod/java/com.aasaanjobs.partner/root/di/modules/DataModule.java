package com.aasaanjobs.partner.root.di.modules;

import android.content.Context;

import com.aasaanjobs.partner.base.data.db.RealmManager;
import com.aasaanjobs.partner.base.data.db.RealmService;
import com.aasaanjobs.partner.base.data.db.realmadapters.RealmTypeAdapterFactory;
import com.aasaanjobs.partner.base.data.preferences.SharedPrefManager;
import com.aasaanjobs.partner.base.data.preferences.SharedPrefService;
import com.aasaanjobs.partner.root.di.customidentifiers.ApplicationContext;
import com.facebook.stetho.Stetho;
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
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nazmuddinmavliwala on 17/05/16.
 */

@Singleton
@Module
public class DataModule {

    private static final String API_BASE_URL = "https://api.aasaanjobs.com/";
    private static final String PARTNER_APP = "AasaanjobsPartner.realm";

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
    public OkHttpClient provideOkHttpClient() {
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
    public RealmInspectorModulesProvider provideInspectorModule(@ApplicationContext Context context) {
        return RealmInspectorModulesProvider.builder(context)
                .withMetaTables()
                .databaseNamePattern(Pattern.compile(".+\\.realm"))
                .build();
    }

    @Singleton
    @Provides
    public Realm provideRealm(@ApplicationContext Context context) {
        RealmConfiguration configuration = new RealmConfiguration
                .Builder(context)
                .name(PARTNER_APP)
                .schemaVersion(1)
                .build();
        return Realm.getInstance(configuration);
    }

    @Singleton
    @Provides
    public Stetho.Initializer provideSeetheInitializer(@ApplicationContext Context context
            , RealmInspectorModulesProvider provider) {
        return  Stetho.newInitializerBuilder(context)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                .enableWebKitInspector(provider)
                .build();
    }

    @Singleton
    @Provides
    public RealmService provideRealmService(Realm realm) {
        return RealmManager.getInstance(realm);
    }

    @Singleton
    @Provides
    public SharedPrefService provideSharedPrefManager(@ApplicationContext Context context) {
        return SharedPrefManager.getInstance(context);
    }

}
