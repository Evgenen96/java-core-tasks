package buildings.util.factories;

import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.util.BuildingFactory;

/**
 * TASK 6.6 Конкретная фабрика офисных зданий
 */
public class OfficeFactory implements BuildingFactory {
    @Override
    public Space createSpace(double area) {
        return new Office(area);
    }

    @Override
    public Space createSpace(double area, int roomsAmount) {
        return new Office(area, roomsAmount);
    }

    @Override
    public Floor createFloor(int spacesAmount) {
        return new OfficeFloor(spacesAmount);
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        return new OfficeFloor(spaces);
    }

    @Override
    public Building createBuilding(int floorsAmount, int[] spacesAmount) {
        return new OfficeBuilding(floorsAmount, spacesAmount);
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        return new OfficeBuilding(floors);
    }
}
