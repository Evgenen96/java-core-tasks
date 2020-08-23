package buildings;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

public class Dwelling implements Building {

    private Floor[] dwellingFloorsArray;

    public Dwelling(int dwellingFloorsAmount, int[] flatOnDwellingFloorAmount) {
        this.dwellingFloorsArray = new DwellingFloor[dwellingFloorsAmount];
        for (int i = 0; i < dwellingFloorsAmount; i++) {
            dwellingFloorsArray[i] = new DwellingFloor(flatOnDwellingFloorAmount[i]);
        }
    }

    public Dwelling(Floor[] dwellingFloorsArray) {
        this.dwellingFloorsArray = dwellingFloorsArray;
    }

    public int getFloorsAmount() {
        return dwellingFloorsArray.length;
    }

    public int getSpacesAmount() {
        int amount = 0;
        for (Floor dwellingFloor : dwellingFloorsArray) {
            amount += dwellingFloor.getSpacesAmount();
        }
        return amount;
    }

    public int getTotalArea() {
        int sum = 0;
        for (Floor dwellingFloor : dwellingFloorsArray) {
            sum += dwellingFloor.getTotalArea();
        }
        return sum;
    }

    public int getRoomsAmount() {
        int sum = 0;
        for (Floor dwellingFloor : dwellingFloorsArray) {
            sum += dwellingFloor.getRoomsAmount();
        }
        return sum;
    }

    public Floor[] getFloorsArray() {
        return dwellingFloorsArray;
    }

    public Floor getFloor(int number) {
        return dwellingFloorsArray[number];
    }

    public void changeFloor(int number, Floor newFloor) {
        dwellingFloorsArray[number] = newFloor;
    }

    //todo
    public Flat getSpace(int number) {
        return null;
    }

    //todo
    public void changeSpace(int number, Space newSpace) {

    }

    //todo
    public void insertSpace(int number, Space newSpace) {

    }

    //todo
    public void removeSpace(int number) {

    }

    //todo
    public Flat getBestSpace() {
        return null;
    }

    //todo
    public Space[] getSpacesAsSortedArray() {
        return null;
    }
}
