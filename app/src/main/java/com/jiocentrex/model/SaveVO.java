package com.jiocentrex.model;

import java.io.Serializable;

public class SaveVO implements Serializable {

    private int gate;
    private int societyOffices;
    private int towerLiftLobby;
    private int clubHouseAndMoreCommonPoint;
    private int societyManagerAndCommitteeMembers;
    private int parking;
    private int societyHousekeepingAndStaff;
    private int shops;
    private int services;


    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public int getSocietyOffices() {
        return societyOffices;
    }

    public void setSocietyOffices(int societyOffices) {
        this.societyOffices = societyOffices;
    }

    public int getTowerLiftLobby() {
        return towerLiftLobby;
    }

    public void setTowerLiftLobby(int towerLiftLobby) {
        this.towerLiftLobby = towerLiftLobby;
    }

    public int getClubHouseAndMoreCommonPoint() {
        return clubHouseAndMoreCommonPoint;
    }

    public void setClubHouseAndMoreCommonPoint(int clubHouseAndMoreCommonPoint) {
        this.clubHouseAndMoreCommonPoint = clubHouseAndMoreCommonPoint;
    }

    public int getSocietyManagerAndCommitteeMembers() {
        return societyManagerAndCommitteeMembers;
    }

    public void setSocietyManagerAndCommitteeMembers(int societyManagerAndCommitteeMembers) {
        this.societyManagerAndCommitteeMembers = societyManagerAndCommitteeMembers;
    }

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public int getSocietyHousekeepingAndStaff() {
        return societyHousekeepingAndStaff;
    }

    public void setSocietyHousekeepingAndStaff(int societyHousekeepingAndStaff) {
        this.societyHousekeepingAndStaff = societyHousekeepingAndStaff;
    }

    public int getShops() {
        return shops;
    }

    public void setShops(int shops) {
        this.shops = shops;
    }

    public int getServices() {
        return services;
    }

    public void setServices(int services) {
        this.services = services;
    }
}
