package interfaces;

import buildings.Flat;

public interface Floor {

    int getSpacesAmount();

    Space[] getSpacesArray();

    double getTotalArea();

    void changeSpace(int number, Space space);

    int getRoomsAmount();

    Space getSpace(int number);

    void insertSpace(int number, Space space);

    void removeSpace(int number);

    Space getBestSpace();
}
