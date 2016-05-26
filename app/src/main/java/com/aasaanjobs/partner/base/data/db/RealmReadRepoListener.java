package com.aasaanjobs.partner.base.data.db;

/**
 * Created by nazmuddinmavliwala on 19/05/16.
 */
public interface RealmReadRepoListener<T> {
    void onSuccess(T t);
    void onError(Throwable error);
}
