package com.aasaanjobs.partner.base.data.db.tables;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 23/05/16.
 */

@RealmClass
public class LanguageDO implements RealmModel {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("slug")
    private String slug;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
