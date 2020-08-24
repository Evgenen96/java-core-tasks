import exceptions.InexchangeableSpacesException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

/*
 * Создайте отдельный класс PlacementExchanger, работающий со ссылками типа Space, Floor, Building и содержащий:
 * •	Метод проверки возможности обмена помещениями.
 * Метод возвращает true, если общая площадь и количество комнат в помещениях равны, и false в других случаях
 * •	Метод проверки возможности обмена этажами. Методу передаются две ссылки на объекты типа Floor.
 * Метод возвращает true, если общая площадь этажей и количество помещений равны, и false в других случаях.
 * •	Метод обмена помещениями двух этажей
 * public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2).
 * •	Метод обмена этажами двух зданий
 * public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2).
 */

public class PlacementExchanger {
    public static boolean areSpacesExchangable(Space spaceA, Space spaceB) {
        if (spaceA.getRoomsAmount() != spaceB.getRoomsAmount()) {
            return false;
        }
        if (spaceA.getArea() != spaceB.getArea()) {
            return false;
        }
        return true;
    }

    public static boolean areFloorsExchangable(Floor floorA, Floor floorB) {
        if (floorA.getTotalArea() != floorB.getTotalArea()) {
            return false;
        }
        if (floorA.getSpacesAmount() != floorB.getSpacesAmount()) {
            return false;
        }
        return true;
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws
            InexchangeableSpacesException {
        Space space1 = floor1.getSpace(index1), space2 = floor2.getSpace(index2);
        if (areSpacesExchangable(space1, space2)) {
            throw new InexchangeableSpacesException();
        }
        floor1.changeSpace(index1, space2);
        floor2.changeSpace(index2, space1);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws
            InexchangeableSpacesException {
        Floor floor1 = building1.getFloor(index1);
        Floor floor2 = building2.getFloor(index2);
        if (areFloorsExchangable(floor1, floor2)) {
            throw new InexchangeableSpacesException();
        }
        building1.changeFloor(index1, floor2);
        building2.changeFloor(index2, floor1);
    }


}
