package buildings.net.server;

import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.Hotel;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import buildings.office.OfficeBuilding;
import buildings.util.BuildingFactory;
import buildings.util.factories.DwellingFactory;
import buildings.util.factories.HotelFactory;
import buildings.util.factories.OfficeFactory;

import java.io.IOException;
import java.net.ServerSocket;

public abstract class Server {

    private static final int DWELLING_COST = 1000;
    private static final int OFFICE_BUILDING_COST = 1500;
    private static final int HOTEL_COST = 2000;
    protected static ServerSocket serverSocket;

    static {
        try {
            serverSocket = new ServerSocket(666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * TASK 8.3
     * Включите в начало метода оценки стоимости проверку на то, не наложен ли на здание арест,
     * и реализуйте в классе сервера метод проверки ареста здания.
     * В рамках учебного задания для простоты будем считать, что метод проверки использует
     * датчик случайных чисел и в среднем сообщает об аресте здания в 10% случаев.
     */
    protected static double getBuildingCost(Building building) throws BuildingUnderArrestException {
        boolean isArrested = Math.random() < 0.1 ? true : false;
        if (!isArrested) {
            if (building instanceof Dwelling) {
                if (building instanceof Hotel) {
                    return building.getTotalArea() * HOTEL_COST;
                } else {
                    return building.getTotalArea() * DWELLING_COST;
                }
            } else {
                if (building instanceof OfficeBuilding) {
                    return building.getTotalArea() * OFFICE_BUILDING_COST;
                }
            }
            throw new BuildingUnderArrestException();
        }
        throw new BuildingUnderArrestException();
    }
    protected static BuildingFactory getFactory(String type) {
        switch (type) {
            case "Dwelling":
                return new DwellingFactory();
            case "Office":
                return new OfficeFactory();
            case "Hotel":
                return new HotelFactory();
            default:
                return null;
        }
    }
}
