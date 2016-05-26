package com.aasaanjobs.partner.base.data.db.tables;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;


/**
 * Created by nazmuddinmavliwala on 21/05/16.
 */
@RealmClass
public class FunctionalAreaDO implements RealmModel {

    @PrimaryKey
    @SerializedName("id")
    private long id;


    @SerializedName("name")
    private String name;

    @SerializedName("is_popular")
    private boolean isPopular;

    @SerializedName("short_name")
    private String shortName;

    @SerializedName("image")
    private String image;

    @SerializedName("synonyms")
    private RealmList<RealmString> synonyms;

    @SerializedName("vacancies")
    private int vacancies;

    @SerializedName("candidate_count")
    private int candidate_count;

    @SerializedName("slug")
    private String slug;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPopular() {
        return isPopular;
    }

    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public int getCandidate_count() {
        return candidate_count;
    }

    public void setCandidate_count(int candidate_count) {
        this.candidate_count = candidate_count;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public RealmList<RealmString> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(RealmList<RealmString> synonyms) {
        this.synonyms = synonyms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
