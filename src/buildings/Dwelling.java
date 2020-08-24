package buildings;

import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class Dwelling implements Building, Cloneable, Serializable {

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
        try {
            return dwellingFloorsArray[number];
        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException(number);
        }
    }

    public void changeFloor(int number, Floor newFloor) {
        try {
            dwellingFloorsArray[number] = newFloor;
        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException(number);
        }
    }

    private void doesSpaceExist(int number) {
        if (!(0 <= number && number < getSpacesAmount())) {
            throw new SpaceIndexOutOfBoundsException(number);
        }
    }

    public Space getSpace(int number) {
        doesSpaceExist(number);
        int current = 0;
        for (Floor floor : dwellingFloorsArray) {
            int size = floor.getSpacesAmount();
            for (int i = 0; i < size; i++) {
                if (current++ == number) {
                    return floor.getSpace(i);
                }
            }
        }
        return null;
    }

    public void changeSpace(int number, Space newSpace) {
        doesSpaceExist(number);
        int current = 0;
        for (Floor floor : dwellingFloorsArray) {
            int size = floor.getSpacesAmount();
            for (int i = 0; i < size; i++) {
                if (current++ == number) {
                    floor.changeSpace(i, newSpace);
                }
            }
        }
    }

    public void insertSpace(int number, Space newSpace) {
        doesSpaceExist(number - 1);
        int currentNumber = 0;
        for (Floor floor : dwellingFloorsArray) {
            if (currentNumber + floor.getSpacesAmount() > number) {
                floor.insertSpace(number - currentNumber, newSpace);
                break;
            }
            currentNumber += floor.getSpacesAmount();
        }
    }

    public void removeSpace(int number) {
        doesSpaceExist(number);
        int currentNumber = 0;
        for (Floor floor : dwellingFloorsArray) {
            if (currentNumber + floor.getSpacesAmount() > number) {
                floor.removeSpace(number - currentNumber);
                break;
            }
            currentNumber += floor.getSpacesAmount();
        }

    }

    public Space getBestSpace() {
        double maxSquare = 0;
        Space flat = null;
        Space bestFlat = new Flat(0, 0);
        for (Floor officeFloor : dwellingFloorsArray) {
            flat = officeFloor.getBestSpace();
            if (flat == null) continue;
            if (maxSquare < flat.getArea()) {
                maxSquare = flat.getArea();
                bestFlat = flat;
            }
        }
        return bestFlat;
    }

    public Space[] getSpacesAsSortedArray() {
        Space[] flats = new Flat[this.getSpacesAmount()];
        int i = 0;
        for (Floor floor : dwellingFloorsArray) {
            for (Space flat : floor.getSpacesArray()) {
                flats[i++] = flat;
            }
        }
        Arrays.sort(flats, new Comparator<Space>() {
            @Override
            public int compare(Space o1, Space o2) {
                if (o1.getArea() >= o1.getArea()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return flats;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dwelling (\n  " + getFloorsAmount() + "\n  ");
        for (Floor floor : getFloorsArray()) {
            stringBuilder.append(floor + ",\n  ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dwelling dwelling = (Dwelling) o;
        return Arrays.deepEquals(dwellingFloorsArray, dwelling.dwellingFloorsArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dwellingFloorsArray);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Dwelling dwelling = (Dwelling) super.clone();
        dwelling.dwellingFloorsArray = this.dwellingFloorsArray.clone();
        for (int i = 0; i < dwellingFloorsArray.length; i++) {
            dwelling.dwellingFloorsArray[i] = (Floor) this.dwellingFloorsArray[i].clone();
        }
        return dwelling;
    }
}
