package buildings;

import interfaces.Floor;
import interfaces.Space;

public class DwellingFloor implements Floor {

    private Space[] flatsArray;

    public DwellingFloor(int flatsAmount) {
        flatsArray = new Flat[flatsAmount];
        for (int i = 0; i < flatsAmount; i++) {
            flatsArray[i] = new Flat();
        }
    }

    public DwellingFloor(Flat[] flatsArray) {
        this.flatsArray = flatsArray;
    }

    @Override
    public int getSpacesAmount() {
        return flatsArray.length;
    }

    @Override
    public Space[] getSpacesArray() {
        return flatsArray;
    }

    @Override
    public double getTotalArea() {
        double sum = 0;
        for (Space space : flatsArray) {
            sum += space.getArea();
        }
        return sum;
    }

    @Override
    public void changeSpace(int number, Space space) {

    }

    @Override
    public int getRoomsAmount() {
        int sum = 0;
        for (Space space : flatsArray) {
            sum += space.getRoomsAmount();
        }
        return sum;
    }

    @Override
    public Space getSpace(int number) {
        return flatsArray[number];
    }

    @Override
    public void insertSpace(int number, Space space) {

    }

    @Override
    public void removeSpace(int number) {

    }

    @Override
    public Space getBestSpace() {
        double max = 0;
        Space bestSpace = null;
        for (Space space : flatsArray) {
            if (max < space.getArea()) {
                max = space.getArea();
                bestSpace = space;
            }
        }
        return bestSpace;
    }
}
