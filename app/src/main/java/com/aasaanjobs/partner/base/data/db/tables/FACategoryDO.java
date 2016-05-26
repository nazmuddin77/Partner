package com.aasaanjobs.partner.base.data.db.tables;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 23/05/16.
 */

@RealmClass
public class FACategoryDO implements RealmModel {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("functional_areas")
    RealmList<FunctionalAreaDO> functionalAreaDOs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<FunctionalAreaDO> getFunctionalAreaDOs() {
        return functionalAreaDOs;
    }

    public void setFunctionalAreaDOs(RealmList<FunctionalAreaDO> functionalAreaDOs) {
        this.functionalAreaDOs = functionalAreaDOs;
    }
}
