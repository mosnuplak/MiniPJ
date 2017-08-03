package com.mfec.minipj.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by E5-473G on 7/23/2017.
 */

public class PeopleItemDao {
    @SerializedName("knownAsName")
    private String knownAsName;
    @SerializedName("imageUrl")
    private String imageUrl;

    public String getKnownAsName() {
        return knownAsName;
    }

    public void setKnownAsName(String knownAsName) {
        this.knownAsName = knownAsName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
