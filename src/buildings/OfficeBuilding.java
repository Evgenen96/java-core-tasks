package buildings;

import interfaces.Floor;
import interfaces.Space;

import java.util.*;

public class OfficeBuilding implements interfaces.Building {

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
        return officeFloorsList.get(number);
    }

    //Создайте метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
    @Override
    public void changeFloor(int number, Floor newOfficeFloor) {
        officeFloorsList.add(number, newOfficeFloor);
        officeFloorsList.remove(number + 1);
    }

    //Создайте метод получения объекта офиса по его номеру в офисном здании.
    @Override
    public Space getSpace(int number) {
        int currentNumber = 0;
        for (Floor officeFloor : officeFloorsList) {
            if (currentNumber + officeFloor.getSpacesAmount() >= number) {
                return officeFloor.getSpace(number - currentNumber);
            }
            currentNumber += officeFloor.getSpacesAmount();
        }
        return null;
    }

    //Создайте метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса.
    @Override
    public void changeSpace(int number, Space newOffice) {
        int currentNumber = 0;
        for (Floor officeFloor : officeFloorsList) {
            if (currentNumber + officeFloor.getSpacesAmount() >= number) {
                officeFloor.changeSpace(number - currentNumber, newOffice);
                break;
            }
            currentNumber += officeFloor.getSpacesAmount();
        }
    }

    //Создайте метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса.
    @Override
    public void insertSpace(int number, Space newOffice) {
        int currentNumber = 0;
        for (Floor officeFloor : officeFloorsList) {
            if (currentNumber + officeFloor.getSpacesAmount() >= number) {
                officeFloor.insertSpace(number - currentNumber, newOffice);
                break;
            }
            currentNumber += officeFloor.getSpacesAmount();
        }
    }

    //Создайте метод удаления офиса по его номеру в здании.
    @Override
    public void removeSpace(int number) {
        int currentNumber = 0;
        for (Floor officeFloor : officeFloorsList) {
            if (currentNumber + officeFloor.getSpacesAmount() >= number) {
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
        Space bestOffice = officeFloorsList.getFirst().getSpace(0);
        for (Floor officeFloor : officeFloorsList) {
            office = officeFloor.getBestSpace();
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
}
