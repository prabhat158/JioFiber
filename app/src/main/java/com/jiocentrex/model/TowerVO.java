package com.jiocentrex.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TowerVO implements Parcelable {

    String sn;
    String buildingId;
    String towers;
    String flats;
    String label;
    String shortCode;


    public TowerVO(String sn, String buildingId, String towers, String flats, String label, String shortCode) {
        this.sn = sn;
        this.buildingId = buildingId;
        this.towers = towers;
        this.flats = flats;
        this.label = label;
        this.shortCode = shortCode;
    }

    public TowerVO(String sn, String buildingId, String flats, String label, String shortCode) {
        this.sn = sn;
        this.buildingId = buildingId;
        this.flats = flats;
        this.label = label;
        this.shortCode = shortCode;
    }

    protected TowerVO(Parcel in) {
        sn = in.readString();
        buildingId = in.readString();
        towers = in.readString();
        flats = in.readString();
        label = in.readString();
        shortCode = in.readString();
    }

    public static final Creator<TowerVO> CREATOR = new Creator<TowerVO>() {
        @Override
        public TowerVO createFromParcel(Parcel in) {
            return new TowerVO(in);
        }

        @Override
        public TowerVO[] newArray(int size) {
            return new TowerVO[size];
        }
    };

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getTowers() {
        return towers;
    }

    public void setTowers(String towers) {
        this.towers = towers;
    }

    public String getFlats() {
        return flats;
    }

    public void setFlats(String flats) {
        this.flats = flats;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sn);
        parcel.writeString(buildingId);
        parcel.writeString(towers);
        parcel.writeString(flats);
        parcel.writeString(label);
        parcel.writeString(shortCode);
    }
}
