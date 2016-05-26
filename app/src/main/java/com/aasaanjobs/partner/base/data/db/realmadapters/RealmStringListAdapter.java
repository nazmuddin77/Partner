package com.aasaanjobs.partner.base.data.db.realmadapters;

import com.aasaanjobs.partner.base.data.db.tables.RealmString;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;

/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
public class RealmStringListAdapter extends TypeAdapter<RealmList<RealmString>> {

    @Override
    public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
        out.beginArray();
        for(RealmString realmString : value) {
            out.value(realmString.getValue());
        }
        out.endArray();

    }

    @Override
    public RealmList<RealmString> read(JsonReader in) throws IOException {
        RealmList<RealmString> realmStrings = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            RealmString realmString = new RealmString();
            realmString.setValue(in.nextString());
            realmStrings.add(realmString);
        }
        in.endArray();
        return realmStrings;
    }
}
