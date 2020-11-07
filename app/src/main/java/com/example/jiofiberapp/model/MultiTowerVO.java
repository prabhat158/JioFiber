package com.example.jiofiberapp.model;

public class MultiTowerVO {

    String serialNumber;
    String societyName;
    String towerName;
    String flatNumbers;
    String label;
    String shortCode;
    String uniqueFlatNumber;

    public MultiTowerVO(String serialNumber, String societyName, String towerName, String flatNumbers, String label, String shortCode, String uniqueFlatNumber) {
        this.serialNumber = serialNumber;
        this.societyName = societyName;
        this.towerName = towerName;
        this.flatNumbers = flatNumbers;
        this.label = label;
        this.shortCode = shortCode;
        this.uniqueFlatNumber = uniqueFlatNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
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

    public String getFlatNumbers() {
        return flatNumbers;
    }

    public void setFlatNumbers(String flatNumbers) {
        this.flatNumbers = flatNumbers;
    }

    public String getUniqueFlatNumber() {
        return uniqueFlatNumber;
    }

    public void setUniqueFlatNumber(String uniqueFlatNumber) {
        this.uniqueFlatNumber = uniqueFlatNumber;
    }
}
