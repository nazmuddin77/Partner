package com.aasaanjobs.partner.base.data.db;

import android.content.Context;

import com.aasaanjobs.partner.base.utils.FileUtil;
import com.aasaanjobs.partner.root.di.customidentifiers.ApplicationContext;

import java.util.List;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */

@Singleton
public class RealmManager implements RealmService {

    private static final String PARTNER_APP = "AasaanjobsPartner";
    public static final long SCHEMA_VERSION = 1;
    private Realm realm;

    public RealmManager(@ApplicationContext final Context context, FileUtil fileUtil) {
        RealmAssetHelper assetHelper = new RealmAssetHelper(context, fileUtil);
        assetHelper.loadDatabaseToStorage(PARTNER_APP, new RealmAssetHelperStorageListener() {
            @Override
            public void onLoadedToStorage(String filePath, RealmAssetHelperStatus status) {
                RealmConfiguration configuration = new RealmConfiguration
                        .Builder(context)
                        .name(filePath)
                        .schemaVersion(SCHEMA_VERSION)
                        .build();
                realm = Realm.getInstance(configuration);
            }
        });
    }

    @Override
    public <T extends RealmModel>void insert(final T t, final RealmRepoListener listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public <T extends RealmModel> void insertAll(final List<T> t, final RealmRepoListener listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public Realm getRealm() {
        return this.realm;
    }

    @Override
    public <T extends RealmModel>void insert(final T t) {realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        });
    }

    @Override
    public <T extends RealmModel> void insertAll(final List<T> t) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(t);
            }
        });
    }

    @Override
    public <T extends RealmModel> RealmResults<T> read(Class<T> clazz) {
        return realm.where(clazz).findAll();
    }

    @Override
    public <T extends RealmModel> T readFirst(Class<T> clazz) {
        return realm.where(clazz).findFirst();
    }

    @Override
    public <T extends RealmModel> void delete(Class<T> clazz) {
        realm.delete(clazz);
    }

    @Override
    public <T extends RealmModel> void deleteAll() {
        realm.deleteAll();
    }

    @Override
    public String getDatabaseName() {
        return realm.getConfiguration().getRealmFileName();
    }
}
