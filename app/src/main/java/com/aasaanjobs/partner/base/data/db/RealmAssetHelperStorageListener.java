package com.aasaanjobs.partner.base.data.db;

/**
 * Created by nazmuddinmavliwala on 24/05/16.
 */
public interface RealmAssetHelperStorageListener {
    void onLoadedToStorage(String filePath, RealmAssetHelperStatus status);
}
