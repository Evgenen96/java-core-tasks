package buildings.util;

import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

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
