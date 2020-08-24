package util;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

/**
 *  TASK 6.5 Абстрактная фабрика зданий
 */
public interface BuildingFactory {

    Space createSpace(double area);

    Space createSpace(double area, int roomsCount);

    Floor createFloor(int spacesCount);

    Floor createFloor(Space[] spaces);

    Building createBuilding(int floorsCount, int[] spacesCounts);

    Building createBuilding(Floor[] floors);

}
