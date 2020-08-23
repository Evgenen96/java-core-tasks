import buildings.Flat;
import buildings.Office;
import buildings.OfficeBuilding;
import buildings.OfficeFloor;
import interfaces.Building;
import interfaces.Space;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] officesAmount = new int[]{1, 3, 5, 6, 1, 5};
        Building offBuilding = new OfficeBuilding(6, officesAmount);
        offBuilding.changeSpace(13, new Office(111125,3));
        offBuilding.removeSpace(13);
        System.out.println(offBuilding.getBestSpace().getArea() + " wsds");
    }
}
