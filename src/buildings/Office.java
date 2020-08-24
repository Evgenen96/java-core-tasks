package buildings;

import interfaces.Space;

import java.io.Serializable;
import java.util.Objects;

public class Office implements Space, Cloneable, Serializable {

    private double area;
    private int roomsAmount;
    private final static int DEF_AREA = 250;
    private final static int DEF_ROOMSAMOUNT = 1;

    public Office() {
        this.area = DEF_AREA;
        this.roomsAmount = DEF_ROOMSAMOUNT;
    }

    public Office(double area) {
        Space.checkArea(area);
        this.area = area;
        this.roomsAmount = DEF_ROOMSAMOUNT;
    }

    public Office(double area, int roomsAmount) {
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
        return "Office (" + area + ", " + roomsAmount + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Double.compare(office.area, area) == 0 &&
                roomsAmount == office.roomsAmount;
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
