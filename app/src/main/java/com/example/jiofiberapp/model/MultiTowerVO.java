package com.example.jiofiberapp.model;

public class MultiTowerVO {

    String sn;
    String buildingId;
    String towers;
    String flats;
    String label;
    String shortCode;


    public MultiTowerVO(String sn, String buildingId, String towers, String flats, String label, String shortCode) {
        this.sn = sn;
        this.buildingId = buildingId;
        this.towers = towers;
        this.flats = flats;
        this.label = label;
        this.shortCode = shortCode;
    }

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
}
