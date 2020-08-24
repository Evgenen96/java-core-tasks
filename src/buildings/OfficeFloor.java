package buildings;

import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class OfficeFloor implements Floor, Cloneable, Serializable {

    private ArrayList<Space> officesList;

    //Конструктор может принимать количество офисов на этаже.
    public OfficeFloor(int officesAmount) {
        officesList = new ArrayList<>();
        for (int i = 0; i < officesAmount; i++) {
            officesList.add(new Office());
        }
    }

    //Конструктор может принимать массив офисов этажа.
    public OfficeFloor(Flat[] officesArray) {
        officesList = new ArrayList<>(Arrays.asList(officesArray));
    }

    //Создайте метод получения количества офисов на этаже.
    @Override
    public int getSpacesAmount() {
        return officesList.size();
    }

    //Создайте метод получения массива офисов этажа.
    @Override
    public Office[] getSpacesArray() {
        return officesList.toArray(new Office[0]);
    }

    //Создайте метод получения общей площади помещений этажа
    @Override
    public double getTotalArea() {
        double sum = 0;
        for (Space office : officesList) {
            sum += office.getArea();
        }
        return sum;
    }

    //Создайте метод получения общего количества комнат этажа.
    @Override
    public int getRoomsAmount() {
        int sum = 0;
        for (Space office : officesList) {
            sum += office.getRoomsAmount();
        }
        return sum;
    }

    //Создайте метод получения офиса по его номеру на этаже.
    @Override
    public Space getSpace(int number) {

        return officesList.get(number);
    }

    //Создайте метод добавления нового офиса на этаже по будущему номеру офиса.
    @Override
    public void insertSpace(int number, Space office) {
        officesList.add(number, office);
    }

    //Создайте метод изменения офиса по его номеру на этаже и ссылке на обновленный офис.
    @Override
    public void changeSpace(int number, Space newOffice) {
        officesList.add(number, newOffice);
        officesList.remove(number + 1);
    }

    //Создайте метод удаления офиса по его номеру на этаже.
    @Override
    public void removeSpace(int number) {
        officesList.remove(number);
    }


    //Создайте метод getBestSpace() получения самого большого по площади офиса этажа.
    @Override
    public Space getBestSpace() {
        double maxSquare = 0;
        Space bestOffice = null;
        for (Space office : officesList) {
            if (maxSquare < office.getArea()) {
                maxSquare = office.getArea();
                bestOffice = office;
            }
        }
        return bestOffice;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OfficeFloor (" + getSpacesAmount() + ", ");
        for (Space space : officesList) {
            stringBuilder.append(space + ", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeFloor that = (OfficeFloor) o;
        return Objects.deepEquals(officesList, that.officesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officesList);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OfficeFloor floor = (OfficeFloor) super.clone();
        floor.officesList = new ArrayList<>();
        for (int i = 0; i < this.officesList.size(); i++) {
            floor.officesList.add((Space) this.officesList.get(i).clone());
        }
        return floor;
    }
}
