package com.aasaanjobs.partner.base.data.db.tables;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by nazmuddinmavliwala on 24/05/16.
 */
@RealmClass
public class LocationDO implements RealmModel {

    @PrimaryKey
    private long id;

    @SerializedName("formatted_address")
    private String formattedAddress;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("location")
    private String location;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("short_formatted_address")
    private String shortFormattedAddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getShortFormattedAddress() {
        return shortFormattedAddress;
    }

    public void setShortFormattedAddress(String shortFormattedAddress) {
        this.shortFormattedAddress = shortFormattedAddress;
    }
}
