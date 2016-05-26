package com.aasaanjobs.partner.base.data.db.realmadapters;

import com.google.gson.JsonSerializer;

import io.realm.RealmList;
import io.realm.RealmModel;

public interface RealmConversionFactory<T extends RealmModel> extends JsonSerializer<RealmList<T>> {

}
