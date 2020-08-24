package buildings;

import interfaces.Space;

import java.io.Serializable;
import java.util.Objects;

public class Flat implements Space, Cloneable, Serializable {

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
