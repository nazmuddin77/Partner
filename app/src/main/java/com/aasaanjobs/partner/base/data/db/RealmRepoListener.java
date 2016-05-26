package com.aasaanjobs.partner.base.data.db;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */
public interface RealmRepoListener {
    void onSuccess();
    void onError(Throwable error);
}
