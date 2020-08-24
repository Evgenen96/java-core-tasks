package util.factories;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;
import util.BuildingFactory;

/**
 *  TASK 6.6 Конкретная фабрика жилых зданий
 */
public class DwellingFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        return new Flat(area);
    }

    @Override
    public Space createSpace(double area, int roomsAmount) {
        return new Flat(area, roomsAmount);
    }

    @Override
    public Floor createFloor(int spacesAmount) {
        return new DwellingFloor(spacesAmount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new DwellingFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsAmount, int[] spacesAmount) {
        return new Dwelling(floorsAmount, spacesAmount);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new Dwelling(floors);
    }
}
