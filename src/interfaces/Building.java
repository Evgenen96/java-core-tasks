package interfaces;

import java.io.Serializable;
import java.util.Iterator;


/**
 * <p>TASK 3.8 Интерфейс здания
 */
public interface Building extends Serializable, Cloneable, Iterable<Floor> {
    //Создайте метод получения общего количества этажей здания.
    int getFloorsAmount();

    //Создайте метод получения общего количества офисов здания.
    int getSpacesAmount();

    //Создайте метод получения общей площади помещений здания.
    int getTotalArea();

    //Создайте метод получения общего количества комнат здания.
    int getRoomsAmount();

    //Создайте метод получения массива этажей офисного здания.
    Floor[] getFloorsArray();

    //Создайте метод получения объекта этажа, по его номеру в здании.
    Floor getFloor(int number);

    //Создайте метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
    void changeFloor(int number, Floor newOfficeFloor);

    //Создайте метод получения объекта офиса по его номеру в офисном здании.
    Space getSpace(int number);

    //Создайте метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса.
    void changeSpace(int number, Space newOffice);

    //Создайте метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса.
    void insertSpace(int number, Space newOffice);

    //Создайте метод удаления офиса по его номеру в здании.
    void removeSpace(int number);

    //Создайте метод getBestSpace() получения самого большого по площади офиса здания.
    Space getBestSpace();

    //Создайте метод получения отсортированного по убыванию площадей массива офисов.
    Space[] getSpacesAsSortedArray();

    //Реализовать глубокое клонирование
    Object clone() throws CloneNotSupportedException;

    //Добавьте в классы помещений Flat и Office реализации метода String toString()
    String toString();

    //Добавьте в классы помещений реализации методов int hashCode().
    int hashCode();

    //Добавьте в классы помещений реализации методов boolean equals(Object object).
    boolean equals(Object object);

}
