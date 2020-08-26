package buildings.interfaces;

import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * <p>TASK 3.6 Интерфейс помещения здания
 */
public interface Space extends Serializable, Cloneable, Comparable<Space> {

    int MAX_AREA = 5000;
    int MAX_ROOMSCOUNT = 20;

    double getArea();

    void setArea(double area);

    int getRoomsAmount();

    void setRoomsAmount(int rooms);

    Object clone() throws CloneNotSupportedException;

    String toString();

    int hashCode();

    boolean equals(Object object);

    /**
     * TASK 6.4 Реализуйте метод int compareTo(T o) таким образом, <br/>
     * чтобы он сравнивал объекты помещений по их площади.
     */
    @Override
    default int compareTo(Space o) {
        return Objects.compare(this, o, Comparator.comparingDouble(Space::getArea));
    }

    static void checkRooms(int rooms) {
        if (rooms < 0 || rooms > MAX_ROOMSCOUNT) {
            throw new InvalidRoomsCountException(rooms);
        }
    }

    static void checkArea(double area) {
        if (area < 0 || area > MAX_AREA) {
            throw new InvalidSpaceAreaException(area);
        }
    }

}
