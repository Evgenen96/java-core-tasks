package interfaces;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

/**
 * <p>TASK 3.7 Интерфейс этажа здания
 */
public interface Floor extends Serializable, Cloneable, Iterable<Space>, Comparable<Floor> {

    int getSpacesAmount();

    Space[] getSpacesArray();

    double getTotalArea();

    void changeSpace(int number, Space space);

    int getRoomsAmount();

    Space getSpace(int number);

    void insertSpace(int number, Space space);

    void removeSpace(int number);

    Space getBestSpace();

    Object clone() throws CloneNotSupportedException;

    String toString();

    int hashCode();

    boolean equals(Object object);

    @Override
    Iterator<Space> iterator();

    /**
     * TASK 6.4 В классах этажей реализуйте метод int compareTo(T o) <br/>
     * таким образом, чтобы он сравнивал объекты этажей по количеству помещений <br/>
     */
    @Override
    default int compareTo(Floor o) {
        return Objects.compare(this, o, Comparator.comparingInt(Floor::getSpacesAmount));
    }

}
