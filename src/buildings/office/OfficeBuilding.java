package buildings.office;

import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.util.*;

/**
 * <ul>
 * <li>TASK 2.1 Класс жилого помещения</li>
 * <li>TASK 5.1 Добавьте в классы зданий Dwelling, OfficeBuilding реализации метода String toString()</li>
 * <li>TASK 5.2 Добавьте в классы зданий реализации методов boolean equals(Object object)</li>
 * <li>TASK 5.3 Добавьте в классы зданий реализации методов int hashCode()</li>
 * <li>TASK 5.4 Добавьте в интерфейс и классы зданий публичный метод Object clone().
 * Реализовать клонирование, которое должно быть глубоким</li>
 * <li>TASK 6.3 Реализация метода интерфейса Iterable</li>
 * </ul>
 */
public class OfficeBuilding implements Building {

    private LinkedList<Floor> officeFloorsList;

    //Конструктор может принимать количество этажей и массив количества офисов по этажам.
    //избыточно? todo
    public OfficeBuilding(int officeFloorsAmount, int[] officeOnOfficeFloorAmount) {
        this.officeFloorsList = new LinkedList<>();
        for (int i = 0; i < officeFloorsAmount; i++) {
            officeFloorsList.add(new OfficeFloor(officeOnOfficeFloorAmount[i]));
        }
    }

    //Конструктор может принимать массив этажей офисного здания.
    public OfficeBuilding(Floor[] officeFloorsArray) {
        officeFloorsList = new LinkedList<>(Arrays.asList(officeFloorsArray));
    }

    //Создайте метод получения общего количества этажей здания.
    @Override
    public int getFloorsAmount() {
        return officeFloorsList.size();
    }

    //Создайте метод получения общего количества офисов здания.
    @Override
    public int getSpacesAmount() {
        int amount = 0;
        for (Floor officeFloor : officeFloorsList) {
            amount += officeFloor.getSpacesAmount();
        }
        return amount;
    }

    //Создайте метод получения общей площади помещений здания.
    @Override
    public int getTotalArea() {
        int sum = 0;
        for (Floor officeFloor : officeFloorsList) {
            sum += officeFloor.getTotalArea();
        }
        return sum;
    }

    //Создайте метод получения общего количества комнат здания.
    @Override
    public int getRoomsAmount() {
        int sum = 0;
        for (Floor officeFloor : officeFloorsList) {
            sum += officeFloor.getRoomsAmount();
        }
        return sum;
    }

    //Создайте метод получения массива этажей офисного здания.
    @Override
    public Floor[] getFloorsArray() {
        return officeFloorsList.toArray(new Floor[0]);
    }

    //Создайте метод получения объекта этажа, по его номеру в здании.
    @Override
    public Floor getFloor(int number) {
        try {
            return officeFloorsList.get(number);
        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException(number);
        }
    }

    //Создайте метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
    @Override
    public void changeFloor(int number, Floor newOfficeFloor) {
        try {
            officeFloorsList.set(number, newOfficeFloor);
        } catch (IndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException(number);
        }
    }

    private boolean doesSpaceExist(int number) {
        if (!(0 <= number && number < getSpacesAmount())) {
            throw new SpaceIndexOutOfBoundsException(number);
        } else {
            return true;
        }
    }

    //Создайте метод получения объекта офиса по его номеру в офисном здании.
    @Override
    public Space getSpace(int number) {
        doesSpaceExist(number);
        int currentNumber = 0;
        for (Floor officeFloor : officeFloorsList) {
            if (currentNumber + officeFloor.getSpacesAmount() > number) {
                return officeFloor.getSpace(number - currentNumber);
            }
            currentNumber += officeFloor.getSpacesAmount();
        }
        return null;
    }

    //Создайте метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса.
    @Override
    public void changeSpace(int number, Space newOffice) {
        doesSpaceExist(number);
        int currentNumber = 0;
        for (Floor officeFloor : officeFloorsList) {
            if (currentNumber + officeFloor.getSpacesAmount() > number) {
                officeFloor.changeSpace(number - currentNumber, newOffice);
                break;
            }
            currentNumber += officeFloor.getSpacesAmount();
        }
    }

    //Создайте метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса.
    @Override
    public void insertSpace(int number, Space newOffice) {
        if (number == getSpacesAmount() || doesSpaceExist(number)) {
            int currentNumber = 0;
            for (Floor officeFloor : officeFloorsList) {
                if (currentNumber + officeFloor.getSpacesAmount() > number) {
                    officeFloor.insertSpace(number - currentNumber, newOffice);
                    break;
                }
                currentNumber += officeFloor.getSpacesAmount();
            }
        }
    }

    //Создайте метод удаления офиса по его номеру в здании.
    @Override
    public void removeSpace(int number) {
        doesSpaceExist(number);
        int currentNumber = 0;
        for (Floor officeFloor : officeFloorsList) {
            if (currentNumber + officeFloor.getSpacesAmount() > number) {
                officeFloor.removeSpace(number - currentNumber);
                break;
            }
            currentNumber += officeFloor.getSpacesAmount();
        }
    }

    //Создайте метод getBestSpace() получения самого большого по площади офиса здания.
    @Override
    public Space getBestSpace() {
        double maxSquare = 0;
        Space office = null;
        Space bestOffice = new Office(0, 0);
        for (Floor officeFloor : officeFloorsList) {
            office = officeFloor.getBestSpace();
            if (office == null) continue;
            if (maxSquare < office.getArea()) {
                maxSquare = office.getArea();
                bestOffice = office;
            }
        }
        return bestOffice;
    }

    //Создайте метод получения отсортированного по убыванию площадей массива офисов.
    @Override
    public Space[] getSpacesAsSortedArray() {
        Space[] offices = new Office[this.getSpacesAmount()];
        int i = 0;
        for (Floor officeFloor : officeFloorsList) {
            for (Space office : officeFloor.getSpacesArray()) {
                offices[i++] = office;
            }
        }
        Arrays.sort(offices, new Comparator<Space>() {
            @Override
            public int compare(Space o1, Space o2) {
                if (o1.getArea() >= o1.getArea()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return offices;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OfficeBuilding officeBuilding = (OfficeBuilding) super.clone();
        officeBuilding.officeFloorsList = new LinkedList<>();
        for (int i = 0; i < this.officeFloorsList.size(); i++) {
            officeBuilding.officeFloorsList.add((Floor) this.officeFloorsList.get(i).clone());
        }
        return officeBuilding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeBuilding that = (OfficeBuilding) o;
        return Objects.deepEquals(officeFloorsList, that.officeFloorsList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OfficeBuilding (\n  " + getFloorsAmount() + "\n  ");
        for (Floor floor : getFloorsArray()) {
            stringBuilder.append(floor + ",\n  ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(")");
        return stringBuilder.toString();

    }

    @Override
    public int hashCode() {
        return Objects.hash(officeFloorsList);
    }

    @Override
    public Iterator<Floor> iterator() {
        return officeFloorsList.iterator();
    }
}
