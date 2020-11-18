package com.example.jiofiberapp.model;

import java.util.ArrayList;
import java.util.List;

public class RangeVO {

//    public static final int maxGateLimit = 99;
//    public static final int maxSocietyOffices = 99;
//    public static final int maxTowerLobbies = 500;
//    public static final int maxSocietyManagerAndCommitteeMembers = 99;
//    public static final int maxClubHouseCommonHallAndMoreCommonPoints = 500;
//    public static final int maxParking = 99;
//    public static final int maxHousekeeping = 99;
//    public static final int maxShops = 99;
//    public static final int maxServices = 99;

    public List<Integer> getGateAndTowerRange() {
        List<Integer> list = new ArrayList<>();
        //  (800-899)
        for (int i = 800; i < 900; i++) { // Total 100
            list.add(i);
        }

        for (int i = 700; i < 800; i++) { // Total 100
            list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 800; i++) { // total 800
            list.add(0);
        }

        return list;
    }

    public List<Integer> getOfficeAndCommonPointRange() {
        List<Integer> list = new ArrayList<>();
        //  (900-999)
        for (int i = 900; i < 1000; i++) { // Total 100
            list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 900; i++) { // total 900
            list.add(0);
        }

        return list;
    }

    public List<Integer> getGateRange() {
        List<Integer> list = new ArrayList<>();
        //  (880-889)  limit 99
        list.add(888);
        for (int i = 880; i < 890; i++) {
            if (i != 888)
                list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 100; i++) {
            list.add(0);
        }

        return list;
    }

    public List<Integer> getSocietyOfficesRange() {
        List<Integer> list = new ArrayList<>();
        //  (980-989) limit 99
        for (int i = 980; i < 990; i++) {
            list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 100; i++) {
            list.add(0);
        }
        return list;
    }

    public List<Integer> getTowerLobbiesRange() {
        //Tower lobbies - limit 500
        List<Integer> list = new ArrayList<>();
        //  (830- 879)
        for (int i = 830; i < 880; i++) {
            list.add(i);
        }
        //  (890-899)
        for (int i = 890; i < 900; i++) {
            list.add(i);
        }
        //   (520-599)
        for (int i = 520; i < 600; i++) {
            list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 500; i++) {
            list.add(0);
        }
        return list;
    }


    public List<Integer> getSocietyManagerAndCommitteeMembersRange() {
        List<Integer> list = new ArrayList<>();
        list.add(999);
//        //   (990-999) Limit 99
//        for (int i = 990; i < 1000; i++) {
//            if (i != 999)
//                list.add(i);
//        }
//
//        int index = list.size();
//        for (int i = index; i < 100; i++) {
//            list.add(0);
//        }
        return list;
    }

    public List<Integer> getClubHouseCommonHallAndMoreCommonPointsRange() {
        List<Integer> list = new ArrayList<>();
        // (910-979) Limit 500
        for (int i = 910; i < 980; i++) {
            list.add(i);
        }

        int index = list.size() ;
        for (int i = index; i < 500; i++) {
            list.add(0);
        }
        return list;
    }

    public List<Integer> getParkingRange() {
        List<Integer> list = new ArrayList<>();
        // (810-819) limit 99
        for (int i = 810; i < 820; i++) {
            list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 100; i++) {
            list.add(0);
        }
        return list;
    }

    public List<Integer> getHouseKeepingRange() {
        List<Integer> list = new ArrayList<>();
        // (820- 829) Limit 99
        for (int i = 820; i < 830; i++) {
            list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 100; i++) {
            list.add(0);
        }
        return list;
    }

    public List<Integer> getCommercialShopRange() {
        List<Integer> list = new ArrayList<>();
        list.add(777);
        //  (710-799) limit 99
        for (int i = 710; i < 800; i++) {
            if (i != 777)
                list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 100; i++) {
            list.add(0);
        }
        return list;
    }

    public List<Integer> getCommercialServicesRange() {
        List<Integer> list = new ArrayList<>();
        list.add(666);
        // (610-699) limit 99
        for (int i = 610; i < 700; i++) {
            if (i != 666)
                list.add(i);
        }

        int index = list.size();
        for (int i = index; i < 100; i++) {
            list.add(0);
        }
        return list;
    }
}
