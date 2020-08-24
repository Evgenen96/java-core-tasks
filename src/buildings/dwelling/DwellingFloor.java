package buildings.dwelling;

import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Floor;
import interfaces.Space;

import java.util.Arrays;
import java.util.Iterator;


/**
 * <ul>
 * <li>TASK 2.1 Класс жилого помещения</li>
 * <li>TASK 5.1 Добавьте в классы этажей DwellingFloor, OfficeFloor реализации метода String toString()</li>
 * <li>TASK 5.2 Добавьте в классы этажей реализации методов boolean equals(Object object)</li>
 * <li>TASK 5.3 Добавьте в классы этажей реализации методов int hashCode()</li>
 * <li>TASK 5.4 Добавьте в интерфейс и классы этажей публичный метод Object clone().
 * Реализовать клонирование, которое должно быть глубоким</li>
 * <li>TASK 6.3 Реализация метода интерфейса Iterable</li>
 * </ul>
 */
public class DwellingFloor implements Floor {

    private Space[] flatsArray;

    public DwellingFloor(int flatsAmount) {
        flatsArray = new Space[flatsAmount];
        for (int i = 0; i < flatsAmount; i++) {
            flatsArray[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] flatsArray) {
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
        try {
            this.flatsArray[number] = space;
        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException(number);
        }
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
        try {
            return flatsArray[number];
        } catch (IndexOutOfBoundsException e) {
            throw new SpaceIndexOutOfBoundsException(number);
        }
    }

    @Override
    public void insertSpace(int number, Space space) {
        if (number != getSpacesAmount()) {
            getSpace(number);
        }

        Space[] oldArray = flatsArray;
        flatsArray = new Space[oldArray.length + 1];
        int i = 0;
        for (; i < number; i++) {
            flatsArray[i] = oldArray[i];
        }
        flatsArray[number] = space;
        for (; i < oldArray.length; i++) {
            flatsArray[i + 1] = oldArray[i];
        }
    }

    @Override
    public void removeSpace(int number) {
        getSpace(number);
        Space[] oldArray = flatsArray;
        flatsArray = new Space[oldArray.length - 1];
        int i = 0;
        for (; i < number; i++) {
            flatsArray[i] = oldArray[i];
        }
        for (; i < flatsArray.length; i++) {
            flatsArray[i] = oldArray[i + 1];
        }
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DwellingFloor (" + getSpacesAmount() + ", ");
        for (Space space : flatsArray) {
            stringBuilder.append(space + ", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwellingFloor that = (DwellingFloor) o;
        return Arrays.deepEquals(flatsArray, that.flatsArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(flatsArray);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        DwellingFloor floor = (DwellingFloor) super.clone();
        floor.flatsArray = this.flatsArray.clone();
        for (int i = 0; i < flatsArray.length; i++) {
            floor.flatsArray[i] = (Space) this.flatsArray[i].clone();
        }
        return floor;
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<Space>() {

            int goingIndex = 0;

            @Override
            public boolean hasNext() {
                return goingIndex < flatsArray.length;
            }

            @Override
            public Space next() {
                Space space = flatsArray[goingIndex];
                goingIndex++;
                return space;
            }
        };
    }

}
