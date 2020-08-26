package buildings.util;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.util.Iterator;

/**
 * TASK 7.3
 * В пакете buildings описать новый класс декоратора SynchronizedFloor,
 * реализующий с обеспечением синхронизации методы интерфейса Floor,
 * а также перегружающий ряд методов класса Object.
 */
public class SynchronizedFloor implements Floor {
    private final Floor floor;

    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public int getSpacesAmount() {
        synchronized (floor) {
            return floor.getSpacesAmount();
        }
    }

    @Override
    public double getTotalArea() {
        synchronized (floor) {
            return floor.getTotalArea();
        }
    }

    @Override
    public int getRoomsAmount() {
        synchronized (floor) {
            return floor.getSpacesAmount();
        }
    }

    @Override
    public Space[] getSpacesArray() {
        synchronized (floor) {
            return floor.getSpacesArray();
        }
    }

    @Override
    public Space getSpace(int index) {
        synchronized (floor) {
            return floor.getSpace(index);
        }
    }

    @Override
    public void changeSpace(int index, Space space) {
        synchronized (floor) {
            floor.changeSpace(index, space);
        }
    }

    @Override
    public void insertSpace(int index, Space space) {
        synchronized (floor) {
            floor.insertSpace(index, space);
        }
    }

    @Override
    public void removeSpace(int index) {
        synchronized (floor) {
            floor.removeSpace(index);
        }
    }

    @Override
    public Space getBestSpace() {
        synchronized (floor) {
            return floor.getBestSpace();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        synchronized (floor) {
            return floor.clone();
        }
    }

    @Override
    public int hashCode() {
        synchronized (floor) {
            return floor.hashCode();
        }
    }

    @Override
    public boolean equals(Object obj) {
        synchronized (floor) {
            return floor.equals(obj);
        }
    }

    @Override
    public String toString() {
        synchronized (floor) {
            return floor.toString();
        }
    }

    @Override
    public int compareTo(Floor o) {
        synchronized (floor) {
            return floor.compareTo(o);
        }
    }

    @Override
    public Iterator<Space> iterator() {
        synchronized (floor) {
            return floor.iterator();
        }
    }
}