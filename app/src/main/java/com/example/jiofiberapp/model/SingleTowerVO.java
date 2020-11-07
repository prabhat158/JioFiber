package com.example.jiofiberapp.model;

public class SingleTowerVO {

    String serialNumber;
    String societyName;
    String flatNumbers;
    String label;
    String shortCode;
    String uniqueFlatNumber;

    public SingleTowerVO(String serialNumber, String societyName, String flatNumbers, String label, String shortCode, String uniqueFlatNumber) {
        this.serialNumber = serialNumber;
        this.societyName = societyName;
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
