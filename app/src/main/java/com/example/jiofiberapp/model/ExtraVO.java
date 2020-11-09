package com.example.jiofiberapp.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExtraVO implements Serializable {

    private boolean societyRepresentative;
    private boolean societyOffice;
    private boolean clubHouse;
    private boolean mainGateSecurity;
    private boolean societyHousekeeping;
    private boolean gardener;
    private boolean grocery;
    private boolean pharmacy;
    private boolean milkShop;
    private boolean parlor;
    private boolean ironingShop;
    private boolean barber;

    private int otherRepresentative;
    private int otherOffice;
    private int societyRepairServices;
    private int poolSportsOthers;
    private int societyResidentsOwnVentures;

    private int towerLiftSecurity;
    private int otherGates;
    private int parking;
    private int maidsCooks;
    private int carBikeWash;
    private int otherHousekeeping;

    private int kiranaItemsShops;
    private int foodOutlets;
    private int householdItems;
    private int personalServices;
    private int householdServices;
    private int repairSupportServices;

    public List<Integer> getKiranaItemsShopsList() {
        List<Integer> list = new ArrayList<>();
        list.add(770);
        list.add(771);
        list.add(772);
        list.add(773);
        list.add(774);
        list.add(775);
        list.add(776);
        list.add(778);
        list.add(779);

        list.add(780);
        list.add(781);
        list.add(782);
        list.add(783);
        list.add(784);
        list.add(785);
        list.add(786);
        list.add(787);
        list.add(788);
        list.add(789);

        list.add(790);
        list.add(791);
        list.add(792);
        list.add(793);
        list.add(794);
        list.add(795);
        list.add(796);
        list.add(797);
        list.add(798);
        list.add(799);
        return list;
    }

    public List<Integer> getFoodOutletsList() {
        List<Integer> list = new ArrayList<>();
        list.add(710);
        list.add(712);
        list.add(713);
        list.add(714);
        list.add(715);
        list.add(716);
        list.add(717);
        list.add(718);
        list.add(719);

        list.add(720);
        list.add(721);
        list.add(722);
        list.add(723);
        list.add(724);
        list.add(725);
        list.add(726);
        list.add(727);
        list.add(728);
        list.add(729);

        return list;
    }

    public List<Integer> getHouseholdItemsList() {

        List<Integer> list = new ArrayList<>();
        list.add(750);
        list.add(751);
        list.add(752);
        list.add(753);
        list.add(754);
        list.add(756);
        list.add(757);
        list.add(758);
        list.add(759);

        list.add(760);
        list.add(761);
        list.add(762);
        list.add(763);
        list.add(764);
        list.add(765);
        list.add(766);
        list.add(767);
        list.add(768);
        list.add(769);
        return list;

    }

    public List<Integer> getPersonalServicesList() {
        List<Integer> list = new ArrayList<>();
        list.add(670);
        list.add(671);
        list.add(672);
        list.add(673);
        list.add(674);
        list.add(675);
        list.add(676);
        list.add(677);
        list.add(678);
        list.add(679);

        list.add(680);
        list.add(681);
        list.add(682);
        list.add(683);
        list.add(684);
        list.add(685);
        list.add(686);
        list.add(687);
        list.add(688);
        list.add(689);
        return list;
    }

    public List<Integer> getHouseholdServicesList() {
        List<Integer> list = new ArrayList<>();
        list.add(630);
        list.add(631);
        list.add(632);
        list.add(634);
        list.add(635);
        list.add(636);
        list.add(637);
        list.add(638);
        list.add(639);

        list.add(660);
        list.add(661);
        list.add(662);
        list.add(663);
        list.add(664);
        list.add(665);
        list.add(667);
        list.add(668);

        list.add(690);
        list.add(691);
        list.add(692);
        list.add(693);
        list.add(694);
        list.add(695);
        list.add(696);
        list.add(697);
        list.add(698);
        list.add(699);

        return list;
    }

    public List<Integer> getRepairSupportServicesList() {
        List<Integer> list = new ArrayList<>();
        list.add(640);
        list.add(641);
        list.add(642);
        list.add(643);
        list.add(644);
        list.add(645);
        list.add(646);
        list.add(647);
        list.add(648);
        list.add(649);


        list.add(650);
        list.add(651);
        list.add(652);
        list.add(653);
        list.add(654);
        list.add(655);
        list.add(656);
        list.add(657);
        list.add(658);
        list.add(659);
        return list;
    }

    public List<Integer> getTowerLiftSecurityList() {
        List<Integer> list = new ArrayList<>();
        list.add(890);
        list.add(891);
        list.add(892);
        list.add(893);
        list.add(894);
        list.add(895);
        list.add(896);
        list.add(897);
        list.add(898);
        list.add(899);
        return list;
    }

    public List<Integer> getOtherGatesList() {
        List<Integer> list = new ArrayList<>();
        list.add(880);
        list.add(881);
        list.add(882);
        list.add(883);
        list.add(884);
        list.add(885);
        list.add(886);
        list.add(887);
        list.add(889);
        return list;
    }


    public List<Integer> getCarBikeWashList() {
        List<Integer> list = new ArrayList<>();
        list.add(850);
        list.add(851);
        list.add(852);
        list.add(853);
        list.add(854);
        list.add(855);
        list.add(856);
        list.add(857);
        list.add(858);
        list.add(859);
        return list;
    }


    public List<Integer> getOtherHousekeepingList() {
        List<Integer> list = new ArrayList<>();
        list.add(840);
        list.add(841);
        list.add(842);
        list.add(843);
        list.add(845);
        list.add(846);
        list.add(847);
        list.add(848);
        list.add(849);
        return list;
    }

    public List<Integer> getParkingList() {
        List<Integer> list = new ArrayList<>();
        list.add(870);
        list.add(871);
        list.add(872);
        list.add(873);
        list.add(874);
        list.add(875);
        list.add(876);
        list.add(877);
        list.add(878);
        list.add(879);
        return list;
    }

    public List<Integer> getMaidsCooksPair() {
        List<Integer> list = new ArrayList<>();
        list.add(860);
        list.add(861);
        list.add(862);
        list.add(863);
        list.add(864);
        list.add(865);
        list.add(866);
        list.add(867);
        list.add(868);
        list.add(869);
        return list;
    }

    public List<Integer> getPoolSportsOthersList() {
        List<Integer> list = new ArrayList<>();
        list.add(960);
        list.add(961);
        list.add(962);
        list.add(963);
        list.add(964);
        list.add(965);
        list.add(967);
        list.add(968);
        list.add(969);
        return list;
    }

    public List<Integer> getSocietyRepairServicesList() {
        List<Integer> list = new ArrayList<>();
        list.add(970);
        list.add(971);
        list.add(972);
        list.add(973);
        list.add(974);
        list.add(975);
        list.add(977);
        list.add(978);
        list.add(979);
        return list;
    }

    public List<Integer> getSocietyResidentsOwnVenturesList() {
        List<Integer> list = new ArrayList<>();
        list.add(930);
        list.add(931);
        list.add(932);
        list.add(933);
        list.add(934);
        list.add(935);
        list.add(936);
        list.add(937);
        list.add(938);
        list.add(939);
        list.add(940);
        list.add(941);
        list.add(942);
        list.add(943);
        list.add(944);
        list.add(945);
        list.add(946);
        list.add(947);
        list.add(948);
        list.add(949);
        list.add(950);
        list.add(951);
        list.add(952);
        list.add(953);
        list.add(954);
        list.add(955);
        list.add(956);
        list.add(957);
        list.add(958);
        list.add(959);
        return list;
    }

    public List<Integer> getOtherOfficeList() {
        List<Integer> list = new ArrayList<>();
        list.add(980);
        list.add(981);
        list.add(982);
        list.add(983);
        list.add(984);
        list.add(985);
        list.add(986);
        list.add(987);
        list.add(989);
        return list;
    }

    public List<Integer> getOtherRepresentativeList() {
        List<Integer> list = new ArrayList<>();
        list.add(990);
        list.add(991);
        list.add(992);
        list.add(993);
        list.add(994);
        list.add(995);
        list.add(996);
        list.add(997);
        list.add(998);
        list.add(999);
        return list;
    }

    public int getParlorCode() {
        return 666;
    }

    public int getIroningShopCode() {
        return 699;
    }

    public int getBarberCode() {
        return 633;
    }

    public int getGroceryCode() {
        return 777;
    }

    public int getPharmacyCode() {
        return 755;
    }

    public int getMilkShopCode() {
        return 711;
    }

    public int getMainGateSecurityCode() {
        return 888;
    }

    public int getSocietyHousekeepingCode() {
        return 844;
    }

    public int getGardenerCode() {
        return 822;
    }

    public int getSocietyRepresentativeCode() {
        return 999;
    }

    public int getSocietyOfficeCode() {
        return 988;
    }

    public int getClubHouseCode() {
        return 966;
    }

    public boolean isSocietyRepresentative() {
        return societyRepresentative;
    }

    public void setSocietyRepresentative(boolean societyRepresentative) {
        this.societyRepresentative = societyRepresentative;
    }

    public boolean isSocietyOffice() {
        return societyOffice;
    }

    public void setSocietyOffice(boolean societyOffice) {
        this.societyOffice = societyOffice;
    }

    public boolean isClubHouse() {
        return clubHouse;
    }

    public void setClubHouse(boolean clubHouse) {
        this.clubHouse = clubHouse;
    }

    public boolean isMainGateSecurity() {
        return mainGateSecurity;
    }

    public void setMainGateSecurity(boolean mainGateSecurity) {
        this.mainGateSecurity = mainGateSecurity;
    }

    public boolean isSocietyHousekeeping() {
        return societyHousekeeping;
    }

    public void setSocietyHousekeeping(boolean societyHousekeeping) {
        this.societyHousekeeping = societyHousekeeping;
    }

    public boolean isGardener() {
        return gardener;
    }

    public void setGardener(boolean gardener) {
        this.gardener = gardener;
    }

    public boolean isGrocery() {
        return grocery;
    }

    public void setGrocery(boolean grocery) {
        this.grocery = grocery;
    }

    public boolean isPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(boolean pharmacy) {
        this.pharmacy = pharmacy;
    }

    public boolean isMilkShop() {
        return milkShop;
    }

    public void setMilkShop(boolean milkShop) {
        this.milkShop = milkShop;
    }

    public boolean isParlor() {
        return parlor;
    }

    public void setParlor(boolean parlor) {
        this.parlor = parlor;
    }

    public boolean isIroningShop() {
        return ironingShop;
    }

    public void setIroningShop(boolean ironingShop) {
        this.ironingShop = ironingShop;
    }

    public boolean isBarber() {
        return barber;
    }

    public void setBarber(boolean barber) {
        this.barber = barber;
    }

    public int getOtherRepresentative() {
        return otherRepresentative;
    }

    public void setOtherRepresentative(int otherRepresentative) {
        this.otherRepresentative = otherRepresentative;
    }

    public int getOtherOffice() {
        return otherOffice;
    }

    public void setOtherOffice(int otherOffice) {
        this.otherOffice = otherOffice;
    }

    public int getSocietyRepairServices() {
        return societyRepairServices;
    }

    public void setSocietyRepairServices(int societyRepairServices) {
        this.societyRepairServices = societyRepairServices;
    }

    public int getPoolSportsOthers() {
        return poolSportsOthers;
    }

    public void setPoolSportsOthers(int poolSportsOthers) {
        this.poolSportsOthers = poolSportsOthers;
    }

    public int getSocietyResidentsOwnVentures() {
        return societyResidentsOwnVentures;
    }

    public void setSocietyResidentsOwnVentures(int societyResidentsOwnVentures) {
        this.societyResidentsOwnVentures = societyResidentsOwnVentures;
    }

    public int getTowerLiftSecurity() {
        return towerLiftSecurity;
    }

    public void setTowerLiftSecurity(int towerLiftSecurity) {
        this.towerLiftSecurity = towerLiftSecurity;
    }

    public int getOtherGates() {
        return otherGates;
    }

    public void setOtherGates(int otherGates) {
        this.otherGates = otherGates;
    }

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public int getMaidsCooks() {
        return maidsCooks;
    }

    public void setMaidsCooks(int maidsCooks) {
        this.maidsCooks = maidsCooks;
    }

    public int getCarBikeWash() {
        return carBikeWash;
    }

    public void setCarBikeWash(int carBikeWash) {
        this.carBikeWash = carBikeWash;
    }

    public int getOtherHousekeeping() {
        return otherHousekeeping;
    }

    public void setOtherHousekeeping(int otherHousekeeping) {
        this.otherHousekeeping = otherHousekeeping;
    }

    public int getKiranaItemsShops() {
        return kiranaItemsShops;
    }

    public void setKiranaItemsShops(int kiranaItemsShops) {
        this.kiranaItemsShops = kiranaItemsShops;
    }

    public int getFoodOutlets() {
        return foodOutlets;
    }

    public void setFoodOutlets(int foodOutlets) {
        this.foodOutlets = foodOutlets;
    }

    public int getHouseholdItems() {
        return householdItems;
    }

    public void setHouseholdItems(int householdItems) {
        this.householdItems = householdItems;
    }

    public int getPersonalServices() {
        return personalServices;
    }

    public void setPersonalServices(int personalServices) {
        this.personalServices = personalServices;
    }

    public int getHouseholdServices() {
        return householdServices;
    }

    public void setHouseholdServices(int householdServices) {
        this.householdServices = householdServices;
    }

    public int getRepairSupportServices() {
        return repairSupportServices;
    }

    public void setRepairSupportServices(int repairSupportServices) {
        this.repairSupportServices = repairSupportServices;
    }
}
