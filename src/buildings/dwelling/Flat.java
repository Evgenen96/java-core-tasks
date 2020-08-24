package buildings.dwelling;

import interfaces.Space;

import java.util.Objects;

/**
 * <ul>
 * <li>TASK 2.1 Класс жилого помещения</li>
 * <li>TASK 5.1 Добавьте в классы помещений Flat и Office реализации метода String toString()</li>
 * <li>TASK 5.2 Добавьте в классы помещений реализации методов boolean equals(Object object)</li>
 * <li>TASK 5.3 Добавьте в классы помещений реализации методов int hashCode()</li>
 * <li>TASK 5.4 Добавьте в интерфейс и классы помещений публичный метод Object clone().
 * Реализовать клонирование, которое должно быть глубоким</li>
 * </ul>
 */
public class Flat implements Space {

    private double area;
    private int roomsAmount;
    private final static int DEF_ROOMCOUNT = 2;
    private final static int DEF_AREA = 50;

    public Flat() {
        this.area = DEF_AREA;
        this.roomsAmount = DEF_ROOMCOUNT;
    }

    public Flat(double area) {
        Space.checkArea(area);
        this.area = area;
        this.roomsAmount = DEF_ROOMCOUNT;
    }

    public Flat(double area, int roomsAmount) {
        Space.checkArea(area);
        Space.checkRooms(roomsAmount);
        this.area = area;
        this.roomsAmount = roomsAmount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void setArea(double area) {
        Space.checkArea(area);
        this.area = area;
    }

    @Override
    public int getRoomsAmount() {
        return roomsAmount;
    }

    @Override
    public void setRoomsAmount(int roomsAmount) {
        Space.checkRooms(roomsAmount);
        this.roomsAmount = roomsAmount;
    }

    @Override
    public String toString() {
        return "Flat (" + area + ", " + roomsAmount + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Double.compare(flat.area, area) == 0 &&
                roomsAmount == flat.roomsAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, roomsAmount);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
