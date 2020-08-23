package interfaces;

import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;

public interface Space {

    final static int MAX_AREA = 5000;
    final static int MAX_ROOMSCOUNT = 20;

    double getArea();

    void setArea(double area);

    int getRoomsAmount();

    void setRoomsAmount(int rooms);

    static void checkRooms(int rooms) {
        if (rooms <= 0 || rooms >= MAX_ROOMSCOUNT) {
            throw new InvalidRoomsCountException();
        }
    }

    static void checkArea(double area) {
        if (area <= 0 || area >= MAX_AREA) {
            throw new InvalidSpaceAreaException();
        }
    }
}
