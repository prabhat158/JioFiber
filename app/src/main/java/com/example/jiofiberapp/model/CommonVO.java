package com.example.jiofiberapp.model;

import java.io.Serializable;

public class CommonVO implements Serializable {

    int shortCode;
    String name;

    public CommonVO(int shortCode, String name) {
        this.shortCode = shortCode;
        this.name = name;
    }

    public int getShortCode() {
        return shortCode;
    }

    public void setShortCode(int shortCode) {
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
