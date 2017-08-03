package com.mfec.minipj.dao;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by E5-473G on 7/24/2017.
 */

public class People extends RealmObject {


    @PrimaryKey
    int peopleId;


    String peopleName;
    int numClick;
    String pictureUrl;

    public int getNumClick() {
        return numClick;
    }

    public void setNumClick(int numClick) {
        this.numClick = numClick;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
